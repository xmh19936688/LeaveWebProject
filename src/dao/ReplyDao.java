package dao;

import java.sql.Connection;

import base.BaseDao;

public class ReplyDao  extends BaseDao {

	public ReplyDao(Connection con) {
		super(con);
	}

	/**
	 * 审批操作
	 * @param userid 学生用户ID
	 * @param startdate 起始日期
	 * @param state 审批结果
	 * @return 操作结果
	 * @throws Exception
	 */
	public int selectReply(String userid, String startdate,String state) throws Exception{
		//生成SQL语句
		String sql="update recordinfo set state='"+state+"' where userid='"+userid+"' and startdate='"+startdate+"'";
		//执行操作
		pstmt = con.prepareStatement(sql);		
		int flag=pstmt.executeUpdate();		
		if(flag!=0){
			return 1;
		}
		else{
			return 0;
		}		
	}
}