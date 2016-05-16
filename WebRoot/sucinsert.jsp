<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生请假条提交成功页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="style/css/transdmin.css" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="style/js/jquery.js"></script>
	<script type="text/javascript" src="style/js/jNice.js"></script>
	<script type="text/javascript" src="calendar.js"></script>
    <!--<script type="text/javascript" src="my.js"></script>-->
  </head>
  
 <body>
	<center>
	<div id="wrapper">
	   	<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    	<h1><a href="#"><span>请假系统</span></a></h1>
    	<div id="container"> 
			<div id="main"> 
    	<h3>
			<form name="mylist" action="mylist.action" method="post">
				<font color='black'>请假条提交成功！<a href="suclogin.jsp">返回首页</a>&raquo;&raquo;</font>
	  		</form>
		</h3>
		</div>
		<div id="container"> </div>
       </div> 
       
    </div>	
    </center>       
</body>
</html>
