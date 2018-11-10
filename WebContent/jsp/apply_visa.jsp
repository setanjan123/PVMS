<html>
<head>
<%@ page import="java.util.*,com.pvms.bean.Country;" %>
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
    String pass_Id=(String)request.getAttribute("pass_Id");
    if(pass_Id==null)
    {
    	response.sendRedirect("/PVMS/jsp/home.jsp");
    	return;
    }
    String occupation=(String)request.getAttribute("occupation");
    ArrayList<Country> countries=(ArrayList<Country>)request.getAttribute("countries");
    session.setAttribute("countries",countries);
    
    
%>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/bootstrap.min.css">
<div class="jumbotron text-center">
<marquee><h1>Passport and Visa Management System</h1></marquee>
</div>
</head>
<body>
    <div class="loginbox">
    <img src="<%= request.getContextPath()%>/images/visa3.jpg" class="avatar">
        <h1>Apply Visa</h1>
        <form method="post" action="<%= request.getContextPath()%>/VisaController">
            <input type="hidden" name="opt" value="opt2">
            <p>User Id: <%= uname %></p>
            <p>Passport Number: <%= pass_Id%> </p>
            <p>Country</p>
            <select name="country">
            <%
            
            for(Country c:countries)
            {
            	%>
            	<option value=<%= c.getId() %>><%= c.getName() %></option>
            <%    
            }
            %>
            </select>
            <p>Visa Type</p>
            <select name="visa_type">
            <% if(occupation.equals("Student"))
               out.println("<option value=\"Student\">Student</option>");
               else
               out.println("<option value=\"Work\">Work</option>");
            %>
            <option value="Tourist">Tourist</option>
            </select>
            <br><br>
 <input type="submit" name="" value="Submit">
  <a href="<%= request.getContextPath()%>/jsp/home.jsp">Back</a>      
           
        </form>  
    </div>

</body>
</head>
</html>
