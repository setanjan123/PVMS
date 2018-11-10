<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message</title>
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); 
%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/bootstrap.min.css">
<div class="jumbotron">
<style>
.loginbox{
    width: 500px;
    height: auto;
    background: #000;
    color: #fff;
    top: 50%;
    left: 50%;
    position: absolute;
    transform: translate(-50%,-50%);
    box-sizing: border-box;
    padding: 70px 30px 30px 30px;
}

.loginbox a{
    text-decoration: none;
    font-size: 1em;
    line-height: 20px;
    color: darkgrey;
}
</style>
<div class="marq">
<marquee><h1>Passport and Visa Management System</h1></marquee>
</div>
</div>
<% String message = (String)request.getAttribute("message"); %>

</head>
<body>

<div class="loginbox">
<p><h1><%= message %></h1></p><br>
<p><a href="/PVMS/HomeController">Click Here to Go to HomePage</a></p>

</div>


</body>
</html>
