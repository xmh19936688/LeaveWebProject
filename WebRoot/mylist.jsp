<%@ page language="java" import="java.util.*,vo.User" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	User user = (User) session.getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>个人请假信息页面</title>

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
<script language=JavaScript>
	function logout() {
		if (confirm("您确定要退出请假系统吗？"))
			top.location = "../show/index.jsp";
		return false;
	}
</script>
</head>

<body>
	<center>
		<div id="wrapper">
			<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
			<h1>
				<a href="#"><span>Transdmin Light</span>
				</a>
			</h1>

			<!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
			<!-- // #end mainNav -->

			<div id="containerHolder">
				<div id="container">
					<div id="sidebar">
						<ul class="sideNav">
							<li><a href="suclogin.jsp">填写请假单</a>
							</li>
							<li><a href="mylist.action">全部请假单信息</a>
							</li>
							<li><a href="unmylist.action">待审批请假单</a>
							</li>
							<li><a href="domylist.action">已审批请假单</a>
							</li>
						</ul>
						<!-- // .sideNav -->
					</div>
					<!-- // #sidebar -->

					<!-- h2 stays for breadcrumbs -->
					<h2>
						<%=user.getUsername()%>欢迎你！ <a href="#" target="_self"
							onClick="logout();"><img src="images/out.gif" alt="安全退出"
							width="46" height="20" border="0">
						</a>
					</h2>
					<div id="main">
						<h3>全部请假单</h3>
						<table cellpadding="0" cellspacing="0">
							<tr height="30" valign="middle">
								<th height="30" width="10%" align="center">姓名</th>
								<th width="15%" align="center">开始日期</th>
								<th width="15%" align="center">结束日期</th>
								<th width="30%" align="center">原因</th>
								<th width="12%" align="center">审批状态</th>
							</tr>
							<s:iterator value="#request.list" id="ask">
								<tr>
									<td align="center"><s:property value="username" /></td>
									<td align="center"><s:property value="startdate" /></td>
									<td align="center"><s:property value="enddate" /></td>
									<td align="center"><s:property value="reason" /></td>
									<td align="center"><s:property value="state" /></td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<!-- // #main -->

					<div class="clear"></div>
				</div>
				<!-- // #container -->
			</div>
		</div>
		<!-- // #wrapper -->
</body>
</html>
