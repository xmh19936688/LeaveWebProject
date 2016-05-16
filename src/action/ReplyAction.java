package action;

import java.sql.Connection;

import constinfo.AgreeInfo;
import dao.ReplyDao;
import utils.DBConn;
import base.BaseAction;

public class ReplyAction  extends BaseAction {
	private String state;
	private String userid;
	private String startdate;

	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * 批准请假
	 * @return 操作结果
	 * @throws Exception
	 */
	public String replyagree() throws Exception{
		Connection con = null;
		try{
			//获取数据库链接
			con = DBConn.getConnection();
			ReplyDao replyDao = new ReplyDao(con);
			state=AgreeInfo.ASK_AGREE;
			//执行数据库操作
			int flag = replyDao.selectReply(userid,startdate,state);
			if(flag==1){
				return "success";
			}
			else{
				return "fail";
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
			DBConn.closeConnection(con);
		}		
		return "fail";	
	}
	
	/**
	 * 驳回请假
	 * @return 操作结果
	 * @throws Exception
	 */
	public String replydisagree() throws Exception{
		Connection con = null;
		try{
			//获取数据库链接
			con = DBConn.getConnection();
			ReplyDao replyDao = new ReplyDao(con);
			state=AgreeInfo.ASK_DISAGREE;
			//执行数据库操作
			int flag = replyDao.selectReply(userid,startdate,state);
			if(flag==1){
				return "success";
			}
			else{
				return "fail";
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
			DBConn.closeConnection(con);
		}		
		return "fail";	
	}
}