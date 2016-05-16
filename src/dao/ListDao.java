package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import vo.Ask;
import base.BaseDao;
import constinfo.AgreeInfo;

public class ListDao extends BaseDao {

	public ListDao(Connection con) {
		super(con);
	}

	// 查询所有记录
	public List<Ask> selectList() {

		String sql = "select username,classid,recordinfo.* from studentinfo inner join recordinfo on studentinfo.userid=recordinfo.userid inner join userinfo on userinfo.userid=recordinfo.userid";
		return execute(sql);
	}

	// 查询所有未审批记录
	public List<Ask> selectUnList() {

		String sql = "select username,classid,recordinfo.* from studentinfo inner join recordinfo on studentinfo.userid=recordinfo.userid inner join userinfo on userinfo.userid=recordinfo.userid where state='"+AgreeInfo.ASK_UNDO+"'";
		return execute(sql);
	}

	// 查询所有已审批记录
	public List<Ask> selectDoList() {
		String sql = "select username,classid,recordinfo.* from studentinfo inner join recordinfo on studentinfo.userid=recordinfo.userid inner join userinfo on userinfo.userid=recordinfo.userid where (state='"+AgreeInfo.ASK_AGREE+"'or state='"+AgreeInfo.ASK_DISAGREE+"')";
		return execute(sql);
	}

	// 根据辅导员兼班主任ID查询辖区记录
	public List<Ask> selectListByAssistantAndHeadTeacherId(String userid) {
		String sql = "select classinfo.classid,userinfo.username,recordinfo.* from classinfo,studentinfo,userinfo,recordinfo where((classinfo.assistantteacherid='"
				+ userid
				+ "' or classinfo.headteacherid='"
				+ userid
				+ "')and studentinfo.classid=classinfo.classid and userinfo.userid=studentinfo.userid and recordinfo.userid=studentinfo.userid)";
		return execute(sql);
	}

	// 根据辅导员兼班主任ID查询辖区"+AgreeInfo.ASK_UNDO+"记录
	public List<Ask> selectUnListByAssistantAndHeadTeacherId(String userid) {
		String sql = "select classinfo.classid,userinfo.username,recordinfo.* from classinfo,studentinfo,userinfo,recordinfo where((classinfo.assistantteacherid='"
				+ userid
				+ "' or classinfo.headteacherid='"
				+ userid
				+ "')and studentinfo.classid=classinfo.classid and userinfo.userid=studentinfo.userid and recordinfo.userid=studentinfo.userid and state='"+AgreeInfo.ASK_UNDO+"')";
		return execute(sql);
	}

	// 根据辅导员兼班主任ID查询辖区已审批记录
	public List<Ask> selectDoListByAssistantAndHeadTeacherId(String userid) {
		String sql = "select classinfo.classid,userinfo.username,recordinfo.* from classinfo,studentinfo,userinfo,recordinfo where((classinfo.assistantteacherid='"
				+ userid
				+ "' or classinfo.headteacherid='"
				+ userid
				+ "')and studentinfo.classid=classinfo.classid and userinfo.userid=studentinfo.userid and recordinfo.userid=studentinfo.userid and (state='"+AgreeInfo.ASK_AGREE+"' or state='"+AgreeInfo.ASK_DISAGREE+"'))";
		return execute(sql);
	}

	// 根据辅导员ID查询辖区记录
	public List<Ask> selectListByAssistantTeacherId(String userid) {

		String sql = "select username,recordinfo.*,classinfo.classid from userinfo,studentinfo,classinfo,recordinfo where(assistantteacherid='"
				+ userid
				+ "' and classinfo.classid=studentinfo.classid and recordinfo.userid=studentinfo.userid and userinfo.userid=recordinfo.userid)";
		return execute(sql);
	}

	// 根据辅导员ID查询辖区"+AgreeInfo.ASK_UNDO+"记录
	public List<Ask> selectUnListByAssistantTeacherId(String userid) {
		String sql = "select username,recordinfo.*,classinfo.classid from userinfo,studentinfo,classinfo,recordinfo where(assistantteacherid='"
				+ userid
				+ "' and classinfo.classid=studentinfo.classid and recordinfo.userid=studentinfo.userid and userinfo.userid=recordinfo.userid and state='"+AgreeInfo.ASK_UNDO+"')";
		return execute(sql);
	}

	// 根据辅导员ID查询辖区已审批记录
	public List<Ask> selectDoListByAssistantTeacherId(String userid) {
		String sql = "select username,recordinfo.*,classinfo.classid from userinfo,studentinfo,classinfo,recordinfo where(assistantteacherid='"
				+ userid
				+ "' and classinfo.classid=studentinfo.classid and recordinfo.userid=studentinfo.userid and userinfo.userid=recordinfo.userid and (state='"+AgreeInfo.ASK_AGREE+"' or state='"+AgreeInfo.ASK_DISAGREE+"'))";
		return execute(sql);
	}

	// 根据班主任ID查询辖区所有记录
	public List<Ask> selectListByHeadTeacherId(String userid) {
		String sql = "select classinfo.classid,userinfo.username,recordinfo.* from classinfo,studentinfo,userinfo,recordinfo where(classinfo.headteacherid='"
				+ userid
				+ "'and studentinfo.classid=classinfo.classid and userinfo.userid=studentinfo.userid and recordinfo.userid=studentinfo.userid)";
		return execute(sql);
	}

	// 根据班主任ID查询辖区"+AgreeInfo.ASK_UNDO+"记录
	public List<Ask> selectUnListByHeadTeacherId(String userid) {
		String sql = "select classinfo.classid,userinfo.username,recordinfo.* from classinfo,studentinfo,userinfo,recordinfo where(classinfo.headteacherid='"
				+ userid
				+ "'and studentinfo.classid=classinfo.classid and userinfo.userid=studentinfo.userid and recordinfo.userid=studentinfo.userid and state='"+AgreeInfo.ASK_UNDO+"')";
		return execute(sql);
	}

	// 根据班主任ID查询辖区已审批记录
	public List<Ask> selectDoListByHeadTeacherId(String userid) {
		String sql = "select classinfo.classid,userinfo.username,recordinfo.* from classinfo,studentinfo,userinfo,recordinfo where(classinfo.headteacherid='"
				+ userid
				+ "'and studentinfo.classid=classinfo.classid and userinfo.userid=studentinfo.userid and recordinfo.userid=studentinfo.userid and (state='"+AgreeInfo.ASK_AGREE+"' or state='"+AgreeInfo.ASK_DISAGREE+"'))";
		return execute(sql);
	}

	// 根据sql语句执行
	private List<Ask> execute(String sql) {
		List<Ask> list = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<Ask>();
			while (rs.next()) {
				Ask ask = new Ask();
				ask.setUserid(rs.getString("userid").trim());
				ask.setClassid(rs.getString("classid").trim());
				ask.setUsername(rs.getString("username").trim());
				ask.setStartdate(rs.getString("startdate").trim());
				ask.setEnddate(rs.getString("enddate").trim());
				ask.setReason(rs.getString("reason").trim());
				ask.setState(rs.getString("state").trim());
				if (!list.contains(ask))
					list.add(ask);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}