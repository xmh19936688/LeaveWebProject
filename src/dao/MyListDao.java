package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import constinfo.AgreeInfo;

import base.BaseDao;
import utils.DBConn;
import vo.Ask;

public class MyListDao extends BaseDao {

	public MyListDao(Connection con) {
		super(con);
	}

	/**
	 * 查询学生所有记录
	 * @param userid 学生用户ID
	 * @return 记录List
	 */
	public List<Ask> selectMyList(String userid) {
		//生成SQL语句
		String sql ="select * from userinfo,recordinfo,studentinfo where (userinfo.userid='" + userid+ "' and recordinfo.userid='" + userid+ "' and studentinfo.userid='" + userid+ "')";
		List<Ask> list = null;
		try {
			//执行SQL语句
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//遍历查询结果添加到list
			list = new ArrayList<Ask>();
			while (rs.next()) {
				Ask ask = new Ask();
				ask.setUserid(userid);
				ask.setClassid(rs.getString("classid").trim());
				ask.setUsername(rs.getString("username").trim());
				ask.setStartdate(rs.getString("startdate").trim());
				ask.setEnddate(rs.getString("enddate").trim());
				ask.setReason(rs.getString("reason").trim());
				ask.setState(rs.getString("state").trim());
				list.add(ask);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询学生未审批记录
	 * @param userid 学生用户ID
	 * @return 请假记录list
	 * @throws Exception
	 */
	public List<Ask> selectUnMyList(String userid) throws Exception {
		//生成SQL语句
		String sql ="select * from userinfo,recordinfo where (userinfo.userid='" + userid+ "' and recordinfo.userid='" + userid+ "' and state='"+AgreeInfo.ASK_UNDO+"')";
		List<Ask> list = null;
		try {
			//执行SQL语句
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//遍历查询结果添加到list
			list = new ArrayList<Ask>();
			while (rs.next()) {
				Ask ask = new Ask();
				ask.setUsername(rs.getString("username").trim());
				ask.setStartdate(rs.getString("startdate").trim());
				ask.setEnddate(rs.getString("enddate").trim());
				ask.setReason(rs.getString("reason").trim());
				ask.setState(rs.getString("state").trim());
				list.add(ask);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询学生已审批记录
	 * @param userid 学生用户ID
	 * @return 请假记录list
	 * @throws Exception
	 */
	public List<Ask> selectDoMyList(String userid) throws Exception {
		//生成SQL语句
		String sql ="select * from userinfo,recordinfo where ((userinfo.userid='"+ userid + "' and recordinfo.userid='"+ userid + "') and (state='"+AgreeInfo.ASK_AGREE+"' or state='"+AgreeInfo.ASK_DISAGREE+"'))";
		List<Ask> list = null;
		try {
			//执行SQL语句
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//遍历查询结果添加到list
			list = new ArrayList<Ask>();
			while (rs.next()) {
				Ask ask = new Ask();
				ask.setUsername(rs.getString("username").trim());
				ask.setStartdate(rs.getString("startdate").trim());
				ask.setEnddate(rs.getString("enddate").trim());
				ask.setReason(rs.getString("reason").trim());
				ask.setState(rs.getString("state").trim());
				list.add(ask);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}