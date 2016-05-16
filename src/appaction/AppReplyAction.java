package appaction;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import utils.DBConn;

import com.opensymphony.xwork2.ActionSupport;

import constinfo.AgreeInfo;
import dao.ReplyDao;

public class AppReplyAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	//批准
	public void replyagree(){
		reply(AgreeInfo.ASK_AGREE);
	}
	
	//驳回
	public void replydisagree(){
		reply(AgreeInfo.ASK_DISAGREE);
	}
	
	//审批操作
	public void reply(String state){
		try{
			//获取提交信息
			HttpServletRequest request = ServletActionContext.getRequest();//获取请求
			String value=request.getParameter("agreeinfo");//提取数据
			JSONObject jsonObject = new JSONObject(value);//解析数据
			String userid=jsonObject.get("userid").toString().trim();
			String startdate=jsonObject.get("startdate").toString().trim();
			//链接数据库
			Connection con = DBConn.getConnection();
			ReplyDao replyDao = new ReplyDao(con);
			
			int flag = replyDao.selectReply(userid,startdate,state);
			if(flag==1){
				//准备回送数据
				jsonObject.put("replyresult", "success");
				//发送数据
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
				response.getWriter().write(jsonObject.toString());//发送数据
				return;
			}
			else{
				//准备回送数据
				jsonObject.put("replyresult", "fail");
				//发送数据
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
				response.getWriter().write(jsonObject.toString());//发送数据
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void setServletResponse(HttpServletResponse arg0) {
	}

	public void setServletRequest(HttpServletRequest arg0) {
	}

}