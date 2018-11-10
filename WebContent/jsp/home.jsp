<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,com.pvms.bean.User,com.pvms.bean.Passport,com.pvms.bean.Visa,java.time.LocalDate" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">





<html>
<head>
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
    User u=(User)session.getAttribute("user");
    Passport pass=u.getP();
    ArrayList<Visa> visas=u.getVisas();
    String []occups={"Private Employee","Government Employee","Retired","Student","Self Employed"};
    LocalDate ld=LocalDate.now();
    String maxDate=ld.getYear()+"-"+ld.getMonthValue()+"-";
    if(ld.getDayOfMonth()<10)
    	maxDate+="0"+ld.getDayOfMonth();
    else
    maxDate+=ld.getDayOfMonth();
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/home.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<%= request.getContextPath()%>/js/home.js"></script>
</head>
<body>
<div class="wrapper">
  

                  <nav>

                        <div class="menu-icon">
                              <i class="fa fa-bars fa-2x"></i>
                        </div>

                        <div class="logo">
                              Passport and Visa Management System
                        </div>

                        <div class="menu">
                              <div id="container"> 
                              
                              <ul>
                                    <li>Welcome <%= uname  %></li>
                                    <li><a href="<%= request.getContextPath()%>/PassportController?opt=opt1">Apply Passport</a></li>
                                    <li><a href="<%= request.getContextPath()%>/VisaController?opt=opt1">Apply Visa</a></li>
                                    <li><a href="<%= request.getContextPath()%>/PassportController?opt=opt6">Passport Renew</a></li>
                                    <li><a href="<%= request.getContextPath()%>/VisaController?opt=opt4">Cancel Visa</a></li>
                                    <li><a href="<%= request.getContextPath()%>/jsp/logout.jsp">Logout</a></li>
                              </ul>


                            
                              </div>
                              
                        </div>
                                   </nav>

         
            <div id="bg">
                  

            </div>

<form id="display" method="post" action="<%= request.getContextPath()%>/HomeController" class="dashbox1">
<input type="hidden" name="opt" value="submission">
<p>First Name</p>
<input type="text" name="fname" value="<%= u.getFirstName() %>" class="inputDisabled" pattern="[a-zA-Z]{1,25}" title="Please enter alphabets only" required disabled>
<p>Last Name</p>
<input type="text" name="lname" value="<%= u.getLastName() %>" class="inputDisabled" pattern="[a-zA-Z]{1,15}" title="Please enter alphabets only" required disabled>
 <p>Date of Birth</p>
  <input type="date" name="dob" value="<%= u.getDob() %>" class="inputDisabled" max="<%= maxDate %>" required disabled>
<p>Address</p>
<input type="text" name="addr" value="<%= u.getAddress() %>" class="inputDisabled" required disabled>
<p>Contact No</p>
<input type="tel" name="tel" value="<%= u.getContactNo() %>" class="inputDisabled" pattern="[0-9]{10}" title="Please enter a 10 digit number" required disabled>
<p>Email</p>
<input type="email" name="email" value="<%=u.getEmail() %>" class="inputDisabled" required disabled>
<p>Educational Qualification</p>
<select name="edu" class="inputDisabled" required disabled>
<option value="<%=u.getEdu() %>"><%= u.getEdu() %></option>
<%
if(u.getEdu().equals("Bachelor's Degree"))
out.println("<option value=\"Master's Degree\">Master's Degree</option>");
else
out.println("<option value=\"Bachelor's Degree\">Bachelor's Degree</option>");
%>
</select>
<br><br>
<p>Occupation</p>
<select name="occup" class="inputDisabled" required disabled>
                            <option value="<%=u.getOccup() %>"><%= u.getOccup() %></option>
                            <%
                             for(String s:occups)
                             {
                            	 if(!s.equals(u.getOccup()))
                                 out.println("<option value=\""+s+"\">"+s+"</option>");
                             }
                            %>
                      </select>
<br><br>
            <input type="submit" id="button1"  value="Edit">  
            </form>   
      </div>
<div class="dashbox2">
<b>Passport Details</b><br><br>
<%
if(pass==null)
out.println("No Passport<br><br>");
else {
%>
<table border="1px solid white">
<tr>
<th>Passport Id</th><th>Issue Date</th><th>Expiry Date</th>
</tr>
<tr>
<td><%= pass.getId() %></td><td><%= pass.getIssuedate() %></td><td><%= pass.getExpirydate() %></td>
</tr>
</table><br><br>
<%
}
%>
<b>Visa Details</b><br><br>
<%
if(visas.isEmpty())
out.println("No Visas<br>");
else{
%>
<table border="1px solid white">
<tr>
<th>Visa Id</th><th>Country</th><th>Visa Type</th><th>Issue Date</th><th>Expiry Date</th>
</tr>
<%
for(Visa v:visas)
out.println("<tr><td>"+v.getVid()+"</td><td>"+v.getCountry().getId()+"</td><td>"+v.getVisaType()+"</td><td>"+v.getDateOfIssue()+"</td><td>"+v.getDateOfExpiry()+"</td></tr>");
}
%>
</table>
</div>
</body>
</html>