package appaction;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import action.ListAction;
import action.MyListAction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserDao;
import utils.DBConn;
import vo.Ask;
import vo.User;

public class AppLoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	/**
	 * 登录
	 * @throws Exception
	 */
	public void login() throws Exception {
		System.out.println("run-xmh");
		//获取登录信息（用户名密码）
		HttpServletRequest request = ServletActionContext.getRequest();//获取请求
		String value=request.getParameter("logininfo");//提取json数据
		JSONObject jsonObject = new JSONObject(value);//解析json数据
		String userid=jsonObject.get("userid").toString().trim();//获取用户名
		String password=jsonObject.get("password").toString().trim();//获取密码
		//链接数据库
		Connection con = DBConn.getConnection();
		try {
			//查询用户
			UserDao userdao = new UserDao(con);
			User user = userdao.selectByUserId(userid);
			//回送数据载体
			jsonObject = new JSONObject();
			//会送数据通路
			HttpServletResponse response = ServletActionContext.getResponse();
			// 用户不存在
			if (user.equals(null)) {
				//准备回送数据
				jsonObject.put("loginresult", "用户不存在");
				//发送数据
				response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
				response.getWriter().write(jsonObject.toString());//发送数据
				return;
			}
			// 密码错误
			else if (!(user.getPassword().equals(password))) {
				//准备回送数据
				jsonObject.put("loginresult", "密码错误");
				//发送数据
				response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
				response.getWriter().write(jsonObject.toString());//发送数据
				return;
			}
			// 学生用户
			else if (user.getPower() == 0) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				//获取该生全部列表
				MyListAction myListAction=new MyListAction();
				String result=myListAction.mylist();
				//判断是否获取成功
				if(result.trim().equals("success")){
					List<Ask> askList=(List<Ask>) request.getAttribute("list");
					// 获取所发送数据的类型
					Type type = new TypeToken<List<Ask>>() {
					}.getType();
					//将要发送的数据转为json
					Gson gson = new Gson();
					String jsonAskList = gson.toJson(askList, type);
					jsonObject.put("loginresult", "student");
					jsonObject.put("myasklist", jsonAskList);
					jsonObject.put("username",user.getUsername());
					//发送数据到Android
					response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
					response.getWriter().write(jsonObject.toString());//发送数据
					return;
				}
				return;
			}
			//教师用户
			else if ( user.getPower() > 0 && user.getPower() < 8) {
				//记录当前用户
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				//获取辖区请假列表
				ListAction listAction=new ListAction();
				String result=listAction.list();
				//获取列表失败
				if(result.trim().equals("fail")){
					String listresult=(String) request.getAttribute("errorinfo");
					//准备回送数据
					jsonObject.put("loginresult", listresult);
					//发送数据
					response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
					response.getWriter().write(jsonObject.toString());//发送数据
					return;
				}
				//列表获取成功则将列表信息向下发送
				List<Ask> askList=(List<Ask>) request.getAttribute("asklist");
				// 获取所发送数据的类型
				Type type = new TypeToken<List<Ask>>() {
				}.getType();
				//将要发送的数据转为json
				Gson gson = new Gson();
				String jsonAskList = gson.toJson(askList, type);
				jsonObject.put("loginresult", "teacher");
				jsonObject.put("asklist", jsonAskList);
				jsonObject.put("username",user.getUsername());
				//发送数据到Android
				response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
				response.getWriter().write(jsonObject.toString());//发送数据
				return;
			}
			//管理员用户
			else if (user.getPower() == 8) {
				//return "adminsuccess";
				return;
			} else {
				//准备回送数据
				jsonObject.put("loginresult", "未知错误");
				//发送数据
				response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
				response.getWriter().write(jsonObject.toString());//发送数据
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.closeConnection(con);
		}
		//准备回送数据
		jsonObject.put("loginresult", "用户不存在");
		//发送数据
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("GBK");//设置字符集防止中文乱码
		response.getWriter().write(jsonObject.toString());//发送数据
		return;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
}