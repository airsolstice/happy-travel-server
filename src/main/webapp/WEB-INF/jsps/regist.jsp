<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  <div><font color="red">${error }</font></div>
    <form action="<c:url value ='/user/regist'/>" method="post">
    	帐号：<input type="text" id="id" name="id" value="${user.id }"><font color="red">${errors.id }</font><br>
    	密码：<input type="password" id="pwd" name="pwd" value=""><font color="red">${errors.pwd }</font><br>
    	昵称：<input type="text" id="name" name="name" value="${user.name }"><font color="red">${errors.name }</font><br>
    	手机号:<input type="text" id="phone" name="phone" value="${user.phone }"><font color="red">${errors.phone }</font><br>
    	email:<input type="text" id="email" name="email" value="${user.email }"><font color="red">${errors.email }</font><br>
    	<input type="submit" value="注册">
    </form>
  </body>
</html>
