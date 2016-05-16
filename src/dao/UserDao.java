package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import base.BaseDao;
import utils.DBConn;
import vo.User;

public class UserDao extends BaseDao{
	
	public UserDao(Connection con) {
		super(con);
	}
	
	/**
	 * 根据用户名和密码登录
	 * @param userid 用户ID
	 * @param password 密码
	 * @return 用户
	 * @throws Exception
	 */
	public User selectByUserIdPassWord(String userid,String password) throws Exception{
		//获取数据库链接
		Connection con = DBConn.getConnection();
		//生成SQL语句
		String sql="select * from userinfo where userid=? and password=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userid);
		pstmt.setString(2, password);
		//执行SQL语句
		rs= pstmt.executeQuery();
		User user=new User();
		if(rs.next()){
			user.setUserid(rs.getString("userid").trim());
			user.setPassword(rs.getString("password").trim());
			user.setUsername(rs.getString("username").trim());
			user.setPower(rs.getInt("power"));
		}
		DBConn.closeAll(pstmt, rs);
		return user;
	}
	/**
	 * 根据用户ID查询用户
	 * @param userid 用户ID
	 * @return 用户与
	 * @throws SQLException
	 */
	public User selectByUserId(String userid) throws SQLException
	{
		//获取数据库链接
		Connection con=DBConn.getConnection();
		//生成SQL有机会
		String sql="select * from userinfo where userid=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,userid);
		//执行数据库操作
		rs=pstmt.executeQuery();
		User user=null;
		if(rs.next()){
			user=new User();
			user.setUserid(rs.getString("userid").trim());
			user.setPassword(rs.getString("password").trim());
			user.setUsername(rs.getString("username").trim());
			user.setPower(rs.getInt("power"));
		}
		DBConn.closeAll(pstmt, rs);
		return user;
	}
}