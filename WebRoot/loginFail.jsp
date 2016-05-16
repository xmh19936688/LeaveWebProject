<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List list = (List) request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>错误页面</title>
<style type="text/css">
#center {
	height: 431px;
	width: 730px;
	background: url(images/spbg.gif);
	margin-top: 50px;
}
</style>
<script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/jNice.js"></script>
<script type="text/javascript" src="calendar.js"></script>
</head>

<body>
	<form name="err" action="">
		<input  name="error" type="hidden"
			value=":( <%=request.getAttribute("errorinfo")%>" />
	</form>
	<center>
		<div id="center">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>
				<h2>抱歉，请<a href="javascript:history.go(-1);">返回</a>后刷新重试</h2> 
			</p>
		</div>
	</center>
</body>
<script language=JavaScript>
	v = document.err['error'].value;
	alert(v);
</script>
</html>
