
<%@page import="java.net.URLDecoder"%>
<%@ page  isELIgnored="false" language="java" import="java.util.*,com.test.servlet.getUserName" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>My JSP 'logInfo.jsp' starting page</title>
    
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
    <h1>你的OpenID为${opid}</h1>
    
 <%     
 		request.setCharacterEncoding("UTF-8");
    	getUserName gun=new getUserName();
    	String opid="";
    	opid=(String)session.getAttribute("opid");
    	String username="";
  		username=gun.getU(opid);
    	if(username!=null)
    	{
    	%>
    	<h2>用户名为：<%=username %></h2>
    	<% 
    	} %>    
         <h2><a href="reg.jsp">注册</a></h2>
  </body>
</html>
