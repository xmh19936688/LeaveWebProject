package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {

//	//驱动器类名
//	private static final String drivername="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	//数据库URL
//	private static final String url="jdbc:sqlserver://localhost:1433;DataBaseName=leave";
//	//数据库登录用户名
//	private static final String user="sa";
//	//数据库登录密码
//	private static final String pwd="123456";
	
		//驱动器类名
		private static final String drivername="com.mysql.jdbc.Driver";
		//数据库URL
		private static final String url="jdbc:mysql://192.168.1.11:3306/de8c854722d6946b3aa2e59ae3c8c782c?useUnicode=true&characterEncoding=UTF-8";
		//数据库登录用户名
		private static final String user="53a57553-1b55";
		//数据库登录密码
		private static final String pwd="20903818-2575";
	
	/**
	 * 获取数据库链接
	 * @return 数据库链接
	 */
	public static Connection getConnection(){
		Connection dbConnection = null;
		try {
		       Class.forName(drivername);
		      dbConnection = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
		      e.printStackTrace();
		}
		return dbConnection;
	}
	
	/**
	 * 关闭数据库链接
	 * @param con
	 */
	public static void closeConnection(Connection con){
		try {
			if(con!=null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭所有
	 * @param s
	 * @param rs
	 */
	public static void closeAll(Statement s, ResultSet rs){
		
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(s!=null)
					s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
	
}