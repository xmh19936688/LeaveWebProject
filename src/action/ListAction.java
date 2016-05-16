package action;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import dao.ClassDao;
import dao.ListDao;
import utils.DBConn;
import vo.Ask;
import vo.User;
import base.BaseAction;

public class ListAction extends BaseAction {

	private List<Ask> asklist;
	private Ask ask;

	public List<Ask> getAsklist() {
		return asklist;
	}

	public void setAsklist(List<Ask> asklist) {
		this.asklist = asklist;
	}

	public Ask getAsk() {
		return ask;
	}

	public void setAsk(Ask ask) {
		this.ask = ask;
	}

	// 查询全部记录
	public String list() throws Exception {
		// 获取用户信息
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int power = user.getPower();
		// 创建到数据库的链接
		Connection con = DBConn.getConnection();
		ListDao listDao = new ListDao(con);

		// 判断是否院长（或管理员）
		if (power >= 4) {
			asklist = (List<Ask>) listDao.selectList();
			request.setAttribute("asklist", asklist);
			return "success";
		}

		// 判断是否班主任兼辅导员
		if (power == 3) {
			asklist = (List<Ask>) listDao
					.selectListByAssistantAndHeadTeacherId(user.getUserid());
			request.setAttribute("asklist", asklist);
			return "success";
		}

		// 判断是否辅导员
		if (power == 2) {
			asklist = (List<Ask>) listDao.selectListByAssistantTeacherId(user
					.getUserid());
			request.setAttribute("asklist", asklist);
			return "success";
		}

		// 判断是否班主任
		if (power == 1) {
			asklist = (List<Ask>) listDao.selectListByHeadTeacherId(user
					.getUserid());
			request.setAttribute("asklist", asklist);
			return "success";
		}
		request.setAttribute("errorinfo", "用户权限不明确");
		return "fail";
	}

	// 查询未审批记录
	public String unlist() throws Exception {
		
		// 获取用户信息
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int power = user.getPower();

		// 创建到数据库的链接
		Connection con = DBConn.getConnection();
		ListDao listDao = new ListDao(con);

		// 判断是否院长（或管理员）
		if (power >= 4) {
			asklist = (List<Ask>) listDao.selectUnList();
			request.setAttribute("asklist", asklist);
			return "success";
		}

		// 判断是否班主任兼辅导员
		if (power == 3) {
			asklist = (List<Ask>) listDao
					.selectUnListByAssistantAndHeadTeacherId(user.getUserid());
			request.setAttribute("asklist", asklist);
			return "success";
		}

		// 判断是否辅导员
		if (power == 2) {
			asklist = (List<Ask>) listDao.selectUnListByAssistantTeacherId(user
					.getUserid());
			request.setAttribute("asklist", asklist);
			return "success";
		}

		// 判断是否班主任
		if (power == 1) {
			asklist = (List<Ask>) listDao.selectUnListByHeadTeacherId(user
					.getUserid());
			request.setAttribute("asklist", asklist);
			return "success";
		}
		request.setAttribute("errorinfo", "用户权限不明确");
		return "fail";
	}

	// 查询已审批记录
	public String dolist() throws Exception {

		// 获取用户信息
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int power = user.getPower();

		// 创建到数据库的链接
		Connection con = DBConn.getConnection();
		ListDao listDao = new ListDao(con);

		// 判断是否院长（或管理员）
		if (power >= 4) {
			asklist = (List<Ask>) listDao.selectDoList();
			request.setAttribute("asklist", asklist);
			return "success";
		}

		// 判断是否班主任兼辅导员
		if (power == 3) {
			asklist = (List<Ask>) listDao
					.selectDoListByAssistantAndHeadTeacherId(user.getUserid());
			request.setAttribute("asklist", asklist);
			return "success";
		}

		// 判断是否辅导员
		if (power == 2) {
			asklist = (List<Ask>) listDao.selectDoListByAssistantTeacherId(user
					.getUserid());
			request.setAttribute("asklist", asklist);
			return "success";
		}

		// 判断是否班主任
		if (power == 1) {
			asklist = (List<Ask>) listDao.selectDoListByHeadTeacherId(user
					.getUserid());
			request.setAttribute("asklist", asklist);
			return "success";
		}
		request.setAttribute("errorinfo", "用户权限不明确");
		return "fail";
	}
}