package action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import base.BaseAction;
import utils.DBConn;
import vo.Ask;
import vo.User;
import dao.AskDao;
import dao.ListDao;

public class AskAction  extends BaseAction{
	
	private Ask ask;
	private String userid;
	private String startdate;
	private List<Ask> list;
	
	
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Ask getAsk() {
		return ask;
	}
	public void setAsk(Ask ask) {
		this.ask = ask;
	}
	public List<Ask> getList() {
		return list;
	}
	public void setList(List<Ask> list) {
		this.list = list;
	}
		
	/**
	 * 添加新请假
	 * @return 操作结果
	 * @throws Exception
	 */
	public String insertask( ) throws Exception{
		
		//从session中获取用户信息
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");

		Connection con = null;
		try {
			//获取数据库链接
			con = DBConn.getConnection();
			AskDao askDao=new AskDao(con);
			//执行数据库操作
			int flag=askDao.insertAsk(ask,user.getUserid());
			if(flag==1){
				return "success";
			}
			else{
				request.setAttribute("errorinfo", "请假失败");
				return "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//关闭数据库链接
			DBConn.closeConnection(con);
		}
		request.setAttribute("errorinfo", "请假失败");
		return "fail";
	}
	
	/**
	 * 详细信息
	 * @return 操作结果
	 * @throws Exception
	 */
	public String detail( ) throws Exception{
		//获取数据库链接
		Connection con = DBConn.getConnection();
		AskDao askDao=new AskDao(con);
		//执行数据库操作
		list = (List<Ask>)askDao.detailAsk(userid,startdate);
		HttpServletRequest request = ServletActionContext.getRequest();
		//将查询结果放到request中
		request.setAttribute("list", list);
		return "success";
	}
	
	/**
	 * 删除记录
	 * @return 操作结果
	 * @throws Exception
	 */
	public String delete( ) throws Exception{
		//获取数据库链接
		Connection con = DBConn.getConnection();
		AskDao askDao=new AskDao(con);
		//执行数据库操作
		int flag = askDao.deleteAsk(userid,startdate);
		if(flag==1){
			return "success";
		}
		else{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("errorinfo", "删除失败");
			return "fail";
		}
	}
}