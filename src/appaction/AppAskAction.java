package appaction;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import utils.DBConn;
import vo.Ask;

import com.opensymphony.xwork2.ActionSupport;

import dao.AskDao;

public class AppAskAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	/**
	 * 添加请假
	 */
	public void ask(){
		Connection con=null;
		//获取客户端请求
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
		//获取请求信息
		HttpServletRequest request = ServletActionContext.getRequest();//获取请求
		String value=request.getParameter("askinfo");//提取json数据
		JSONObject jsonObject = new JSONObject(value);//解析json数据
		//获取请假数据
		String userid=jsonObject.get("userid").toString().trim();
		String startdate=jsonObject.get("startdate").toString().trim();
		String enddate=jsonObject.get("enddate").toString().trim();
		String reason=jsonObject.get("reason").toString();
		String state=jsonObject.get("state").toString().trim();
		//获取数据库链接
		con = DBConn.getConnection();
		AskDao askDao=new AskDao(con);
		//执行数据库操作
		int flag=askDao.insertAsk(new Ask(userid,startdate,enddate,reason,state), userid);
		if(flag==1){
			//回送操作结果
			jsonObject.put("askresult", "success");
			response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
			response.getWriter().write(jsonObject.toString());//发送数据
			return;
		}else{
			//回送操作结果
			jsonObject.put("askresult", "fail");
			response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
			response.getWriter().write(jsonObject.toString());//发送数据
			return;
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.closeConnection(con);
		}
	}

	public void setServletResponse(HttpServletResponse arg0) {
	}

	public void setServletRequest(HttpServletRequest arg0) {
	}

	
}