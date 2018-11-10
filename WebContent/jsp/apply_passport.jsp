<html>
<%@page import="java.util.*,com.pvms.bean.*" %>
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
    ArrayList<State> states = (ArrayList<State>)session.getAttribute("States");
    
%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src = "<%= request.getContextPath()%>/js/passport.js"></script>
<div class="jumbotron">
<div class="marq">
<marquee><h1>Passport and Visa Management System</h1></marquee>
</div>
</div>
</head>
<body>
    <div class="loginbox">
    <img src="<%= request.getContextPath()%>/images/us-passport1.jpg" class="avatar">
        <h1>Apply Passport</h1>
        <form method = "POST" action = "<%= request.getContextPath()%>/PassportController">
        <input type = "hidden" name = "opt" value = "opt5">
            <p>User Id: <%= uname %> </p>
            
<p>State:</p>
<select name="States" id = "dropdown1"  required>
<option>Select</option>                          <%
            for(State s: states)
            {
            	%>
            <option value=<%= s.getId() %>><%= s.getName() %></option>
            <%    
            }
            
            %>
</select>
<p>City</p>
<select name="Cities" id="dropdown2" required>
                            
                            </select>

<p>Passport Seva Kendra</p>
<select name="PSK"  id = "dropdown3" required> 
                            
                            </select>

<p>Pin</p>
<input type="text" name="PIN" placeholder="Enter your pin" pattern="[0-9]{6}" required>

<p>Type of Service</p>
<select name="servicetype" required>
                            <option value="normal">Normal</option>
                            <option value="tatkal">Tatkal</option>
                            </select>
<p>Booklet Type</p>
<select name="booklettype" required>
                            <option value="30">30 pages</option>
                            <option value="60">60 pages</option>
                            </select>

<br><br>
 <input type="submit" name="" value="Submit">
 <a href="<%= request.getContextPath()%>/jsp/home.jsp">Back</a>    
           
        </form>
 
        
    </div>

</body>
</head>
</html>
