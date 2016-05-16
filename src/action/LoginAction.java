package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import dao.UserDao;
import utils.DBConn;
import vo.User;

public class LoginAction {
	private String userid;
	private String password;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//登录默认操作
	public String execute() throws Exception {
		//获取数据库链接
		Connection con = DBConn.getConnection();
		try {
			UserDao userdao = new UserDao(con);
			User user = userdao.selectByUserId(userid);
			// 用户不存在
			if (user.equals(null)) {
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("errorinfo", "用户不存在");
				return "fail";
			}
			// 密码错误
			else if (!(user.getPassword().equals(password))) {
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("errorinfo", "密码错误");
				return "fail";
			}
			// 学生用户
			else if (user.getPower() == 0) {
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				System.out.println("runed-xmh");
				return "studentsuccess";
			}
			//教师用户
			else if ( user.getPower() > 0 && user.getPower() < 8) {
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				return "teachersuccess";
			}
			//管理员用户
			else if (user.getPower() == 8) {
				//return "adminsuccess";
				return "teachersuccess";
			} else {
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("errorinfo", "未知错误");
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.closeConnection(con);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("errorinfo", "用户不存在");
		return "fail";
	}
}