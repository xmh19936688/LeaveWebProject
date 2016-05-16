package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;

import constinfo.AgreeInfo;

import vo.Ask;

import base.BaseDao;

public class AskDao extends BaseDao {
	
	public AskDao(Connection con) {
		super(con);
	}

	/**
	 * 添加请假申请
	 * @param ask 记录对象
	 * @param userid 用户id
	 * @return 操作结果
	 * @throws Exception
	 */
	public int insertAsk(Ask ask,String userid) throws Exception{
		//生成SQL语句
		String sql="insert recordinfo values(?,?,?,?,?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userid.trim());
		pstmt.setString(2, ask.getStartdate().trim());
		pstmt.setString(3, ask.getEnddate().trim());
		pstmt.setString(4, ask.getReason().trim());
		pstmt.setString(5, AgreeInfo.ASK_UNDO.trim());
		//执行SQL语句
		int flag=pstmt.executeUpdate();		
		
		if(flag!=0){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	/**
	 * 查询详细信息
	 * @param userid 用户ID
	 * @param startdate 起始日期
	 * @return
	 */
	public List<Ask> detailAsk(String userid,String startdate){
		//生成SQL语句
		String sql="select * from recordinfo,userinfo where recordinfo.userid = '"+userid+"' and userinfo.userid='"+userid+"' and startdate='"+startdate+"'";
		List<Ask> list =null;
		try{
			//执行SQL语句
			pstmt = con.prepareStatement(sql);
			//获取查询结果
			rs = pstmt.executeQuery();
			list = new ArrayList<Ask>();
			//遍历结果列表，添加到list
			if(rs.next()){
				Ask ask = new Ask();
				ask.setUsername(rs.getString("username").trim());
				ask.setStartdate(rs.getString("startdate").trim());
				ask.setEnddate(rs.getString("enddate").trim());
				ask.setReason(rs.getString("reason").trim());
				ask.setState(rs.getString("state").trim());			
				list.add(ask);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
		return list;		
	}
	/**
	 * 删除请假记录
	 * @param userid 用户ID
	 * @param startdate 起始日期
	 * @return 操作结果
	 * @throws Exception
	 */
	public int deleteAsk(String userid,String startdate)throws Exception{
		//生成SQL语句
		String sql="delete from  recordinfo where userid = '"+userid+"' and startdate='"+startdate+"'";
		//执行SQL语句
		pstmt = con.prepareStatement(sql);
		int flag = pstmt.executeUpdate();		  
		if(flag!=0){
			return 1;
		}
		else{
			return 0;
		}	
	}
}