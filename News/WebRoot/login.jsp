<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教育智慧党建网</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<div class="continer">
  		<h1>教育智慧党建网</h1>
  		<form action="Login" method="post">
  			<table class="log_warp">
	    		<tr>
	    			<td>用户名：</td>
	    			<td><input id="username" name="username" type="text" /> </td>
	    		</tr>
	    		<tr>
	    			<td>密码：</td>
	    			<td><input id="password" name="pwd" type="password" /> </td>
	    		</tr>
	    		<tr>
	    			<td colspan="2"><input type="submit" value="提交"/> </td>
	    		</tr>
	    	</table>
  		</form>
  	</div>
  </body>
</html>
