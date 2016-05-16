package action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import dao.ListDao;
import dao.MyListDao;
import utils.DBConn;
import vo.Ask;
import vo.User;
import base.BaseAction;

public class MyListAction extends BaseAction {
	
	private String userid;
	private List<Ask> list;
	private Ask ask;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<Ask> getList() {
		return list;
	}
	public void setList(List<Ask> list) {
		this.list = list;
	}
	public Ask getAsk() {
		return ask;
	}
	public void setAsk(Ask ask) {
		this.ask = ask;
	}
	
	/**
	 * 学生全部记录
	 * @return 操作结果
	 * @throws Exception
	 */
	public String mylist() throws Exception{
		//从session中获取用户信息
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		userid=user.getUserid();
		//获取数据库链接
		Connection con = DBConn.getConnection();
		MyListDao mylistDao = new MyListDao(con);
		//执行数据库操作
		list =(List<Ask>)mylistDao.selectMyList(userid);
		//将查询结果放入request
		request.setAttribute("list", list);
		return "success";		
	}
	
	/**
	 * 学生未审批记录
	 * @return 操作结果
	 * @throws Exception
	 */
	public String unmylist() throws Exception{
		//从session中获取用户信息
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		userid=user.getUserid();
		//获取数据库链接
		Connection con = DBConn.getConnection();
		MyListDao mylistDao = new MyListDao(con);
		//执行数据库操作
		list =(List<Ask>)mylistDao.selectUnMyList(userid);
		//将查询结果放入request
		request.setAttribute("list", list);
		return "success";		
	}
	
	/**
	 * 学生已审批记录
	 * @return
	 * @throws Exception
	 */
	public String domylist() throws Exception{
		//从session中获取用户信息
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		userid=user.getUserid();
		//获取数据库链接
		Connection con = DBConn.getConnection();
		MyListDao mylistDao = new MyListDao(con);
		//执行数据库操作
		list =(List<Ask>)mylistDao.selectDoMyList(userid);
		//将查询结果放入request
		request.setAttribute("list", list);
		return "success";		
	}
}