<%@ page language="java" import="java.util.*,vo.*,java.util.Date"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	Date today = new Date();
	User user = (User) session.getAttribute("user");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>学生请假页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="style/css/transdmin.css" rel="stylesheet" type="text/css"
	media="screen" />
<script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/jNice.js"></script>
<script type="text/javascript" src="calendar.js"></script>
<!--<script type="text/javascript" src="my.js"></script>-->
<script language=JavaScript>
	function logout() {
		if (confirm("您确定要退出请假系统吗？")) {
			top.location = "login.html";
		}
		return false;
	}
	function check() {
		var v;
		
		v=trim(document.ask['ask.startdate'].value); 
		if(v.length==0)
		{alert("请输入请假日期");
		return false;}
		
		v=trim(document.ask['ask.enddate'].value); 
		if(v.length==0)
		{alert("请输入请假日期");
		return false;}
		
		v=trim(document.ask['ask.reason'].value); 
		if(v.length==0)
		{alert("请输入请假原因");
		return false;}
		return true;
	}
	function trim(s) {
		var count = s.length;
		var st = 0; // start  
		var end = count - 1; // end  

		if (s == "")
			return s;
		while (st < count) {
			if (s.charAt(st) == " ")
				st++;
			else
				break;
		}
		while (end > st) {
			if (s.charAt(end) == " ")
				end--;
			else
				break;
		}
		return s.substring(st, end + 1);
	}
</script>
</head>

<body>
	<center>
		<div id="wrapper">
			<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
			<h1>
				<a href="#"><span>请假系统</span> </a>
			</h1>
			<div id="container">
				<div id="sidebar">
					<ul class="sideNav">

						<li><a href="suclogin.jsp">填写请假单</a></li>
						<li><a href="mylist.action">全部请假单信息</a></li>
						<li><a href="unmylist.action">待审批请假单</a></li>
						<li><a href="domylist.action">已审批请假单</a></li>
					</ul>
					<!-- // .sideNav -->
				</div>

				<h2>
					<%=user.getUsername()%>
					欢迎你！ <a onClick="logout();"> <img src="images/out.gif"
						alt="安全退出" width="46" height="20" border="0"> </a>
				</h2>
				<div id="main">
					<form name="ask" action="ask.action" method="post"
						onsubmit="return check();"><!-- 此处在Myeclipse10下会报错,不影响运行 -->
						<h3>填写请假单</h3>
						<fieldset>
							<p>姓名:</p>
							<p>
								<input name="ask.username" type="text" class="text-medium"
									value="<%=user.getUsername()%>" readonly="readonly" />
							</p>
							<p>请假时间:</p>
							<p>
								<!-- 定义开始时间以及其表示 -->
								<!-- 参数maxlength：输入框中允许输入字符的最大数。  -->
								从<input name="ask.startdate" type="text" id="workStime"
									onClick="new Calendar().show(this);" size="20" maxlength="10"
									readonly="readonly">
									
								<!-- 定义结束时间以及其表示 -->
								到<input name="ask.enddate" type="text" id="workStime"
									onClick="new Calendar().show(this);" size="20" maxlength="10"
									readonly="readonly">
							</p>
							<p>请假理由:</p>
							<p>
								<textarea name="ask.reason" rows="1" cols="1"></textarea>
							</p>
							<input type="submit" value="提交" />
						</fieldset>
					</form>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</center>

</body>
</html>
