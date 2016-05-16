package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.views.xslt.ArrayAdapter;

import base.BaseDao;

public class ClassDao extends BaseDao {
	
	public ClassDao(Connection con) {
		super(con);
	}

	/**
	 * 根据辅导员用户ID查询辖区班级
	 * @param userid 辅导员用户ID
	 * @return
	 */
	public List<String> selectClassInfoByUserId(String userid){
		//生成SQL语句
		String sql="select * from classinfo where assistantteacherid='"+userid+"'";
		List<String> list=null;
		try{
			//执行SQL语句
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			list=new ArrayList<String>();
			//遍历查询结果放入list
			if(rs.next()){
				list.add(rs.getString("classid").trim());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}