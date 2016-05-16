package base;

import java.sql.Connection;
import java.sql.SQLException;

import utils.DBConn;

public abstract class BaseAction {
	/** con Connection 数据库连接对象 */
	protected Connection con;
	
	/** 
	 * openCon方法用于获取数据库连接对象
	 * 
	 * @throws SQLException
	 */
	protected void openCon() throws SQLException{
		con = DBConn.getConnection();
	}
	
	/**
	 * closeCon方法用于关闭与数据库相关的操作
	 * 
	 * @param stmt	Statement	命令执行对象
	 * @param rs	ResultSet	结果集对象
	 * @throws SQLException
	 */
	protected void closeCon() throws SQLException{
		DBConn.closeConnection(con);
	}
}