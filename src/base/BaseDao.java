package base;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBConn;

/**
 * 所有dao类的父类
 * @author stone
 * @since
 * */
public abstract class BaseDao {
	/** con Connection 数据库连接对象 */
	protected Connection con;
	
	protected int flag=0;
	protected int rows=0;
	protected int row=0;
	protected int count=0;
	protected boolean valid=false;
	
	
	/** stmt Statement 命令执行对象 */
	protected Statement stmt;
	
	/** pstmt PreparedStatement 预编译命令执行对象 */
	protected PreparedStatement pstmt;
	protected PreparedStatement patmt;
	/** cstmt CallableStatement 存储过程调用命令执行对象*/
	protected CallableStatement cstmt;
	
	/** rs ResultSet 结果集存储 */
	protected ResultSet rs;
	

	/**
	 * 带参构造函数，所有子类必须调用本方法
	 * 
	 * @param con
	 */
	
	public BaseDao(Connection con){
		this.con = con;	
	}
	/**
	 * closeAll方法关闭ResultSet对象和Statement对象和链接
	 * 
	 * @param rs	结果集对象
	 * @param stmt	命令执行对象
	 * @throws SQLException
	 */
	protected void closeAll(ResultSet rs,Statement stmt) throws SQLException{
		DBConn.closeAll(stmt, rs);
	}
}