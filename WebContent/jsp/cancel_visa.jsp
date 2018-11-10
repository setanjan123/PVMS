<html>
<head>
<%@page import="java.util.List,com.pvms.bean.Visa" %>
<% 
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); 
    String uname=(String)session.getAttribute("uname");
    if(uname==null)
    {
    	response.sendRedirect("/PVMS/html/login.html");
    	return;
    }
    List<Visa> visas=(List<Visa>)request.getAttribute("visas");
    String passId=(String)request.getAttribute("passid");
    if(visas.size()==0||passId==null)
    response.sendRedirect("/PVMS/jsp/home.jsp");
%>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/style.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/bootstrap.min.css">
<div class="jumbotron text-center">
<marquee><h1>Passport and Visa Management System</h1></marquee><br>
</div>
<body>
    <div class="loginbox">
    <img src="<%= request.getContextPath()%>/images/visacancel.jpg" class="avatar">
        <h1>Visa Cancellation</h1>
        <form method="post" action="<%= request.getContextPath()%>/VisaController">
<input type="hidden"  name="opt" value="opt5">
<p>User ID: <%= uname %></p><br>
<p>Passport Number: <%= passId %></p><br>
<p>Select a Visa :</p>
<select name="vid">
<%
  for(Visa visa:visas)
  {
%>
<option value="<%= visa.getVid() %>"><% out.println(visa.getVid()+" "+visa.getCountry().getId()+" "+visa.getVisaType());%>
</option>
<%
}
%>
</select>
<br><br>
<input type="submit" name="" value="Submit">
<a href="<%= request.getContextPath()%>/jsp/home.jsp">Back</a>         
           
        </form>
    </div>

</body>
</head>
</html>