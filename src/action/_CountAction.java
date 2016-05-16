package action;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import dao._CountDao;
import dao.ListDao;
import utils.DBConn;
import vo.Ask;
import vo._Count;
import base.BaseAction;

public class _CountAction extends BaseAction{
	private List<_Count> list;
	private _Count count;
	public List<_Count> getList() {
		return list;
	}
	public void setList(List<_Count> list) {
		this.list = list;
	}
	public _Count getCount() {
		return count;
	}
	public void setCount(_Count count) {
		this.count = count;
	}

	public String countlist() throws Exception{
		Connection con = DBConn.getConnection();
		_CountDao countDao=new _CountDao(con);
		list = (List<_Count>)countDao.selectCountList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "success";
	}

}
