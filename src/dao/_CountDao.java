package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import vo.Ask;
import vo._Count;

import base.BaseDao;

public class _CountDao extends BaseDao {
	
	public _CountDao(Connection con) {
		super(con);
	}
	
	public List<_Count> selectCountList(){
		String sql="select count(*) from content group by username";
		List<_Count> list =null;
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<_Count>();
			while(rs.next()){
				_Count count = new _Count();				
				//count.setCount(rs.getb));	
				count.setUsername(rs.getString("username"));			
				list.add(count);
			}			
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
		return list;		
	}

}
