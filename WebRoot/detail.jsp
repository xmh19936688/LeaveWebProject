<%@ page language="java" import="java.util.*,vo.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) session.getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>打印请假单</title>

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
		if (confirm("您确定要退出请假系统吗？"))
			top.location = "../show/index.jsp";
		return false;
	}
	function check() {
		var v = trim(document.ask['ask.state'].value);
		if (v == '不同意') {
			if (!confirm("请假未通过，仍然打印吗？")) {
				return false;
			}
		}
		window.focus();
		window.print();
		return false;
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

			<div id="main">
				<form name="ask" action="" method="post">
					<s:iterator value="#request.list" id="ask">

						<h3>请假单</h3>
						<fieldset>
							<p>姓名:</p>
							<p>
								<input name="ask.username" type="text" class="text-medium"
									value="<s:property value="username"/>" readonly="readonly" />
							</p>
							<!-- <p>班级:</p><p>
                        		<input name="ask.classmate" type="text" class="text-medium" value="<s:property value="classmate"/>" readonly="readonly" /></p>                       	
                              -->
							<p>请假时间:</p>
							<p>
								从<input name="ask.startdate" type="text" id="workStime"
									value="<s:property value="startdate"/>" size="20"
									maxlength="10" readonly="readonly">
								<!-- 定义结束时间以及其表示 -->
								到<input name="ask.enddate" type="text" id="workStime"
									value="<s:property value="startdate"/>" size="20"
									maxlength="10" readonly="readonly">
							</p>
							<p>请假理由:</p>
							<p>
								<textarea name="ask.reason" rows="1" cols="1"
									readonly="readonly">
									<s:property value="reason" />
								</textarea>
							</p>
							<p>老师意见：</p>
							<p>
								<input name="ask.state" type="text" class="text-medium"
									value="<s:property value="state"/>" readonly="readonly" />
							</p>
							<p>
								<input type="button" value="打印" onsubmit="" onclick="check();">
						</fieldset>
					</s:iterator>
				</form>
			</div>
			<!-- // #main -->

			<div class="clear"></div>
	</center>

</body>
</html>
