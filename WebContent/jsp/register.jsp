<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../js/registration.js"></script>
 <div class="jumbotron text-center">
 <%@page import="java.time.LocalDate" %>
<marquee><h1>Passport and Visa Management System</h1></marquee><br>
</div>
<%

LocalDate ld=LocalDate.now();
String maxDate=ld.getYear()+"-"+ld.getMonthValue()+"-";
if(ld.getDayOfMonth()<10)
	maxDate+="0"+ld.getDayOfMonth();
else
maxDate+=ld.getDayOfMonth();
%>
</head>
<body>
    <div class="loginbox">
    <img src="../images/avatar1.jpg" class="avatar">
    <h2>Register</h2>
    <form id="register">
         <p>First Name</p>
            <input type="text" name="fname" placeholder="Enter First name" pattern="[a-zA-Z]{1,25}" title="Please enter alphabets only" required>
            <p>Last Name</p>
            <input type="text" name="lname" placeholder="Enter Last name" pattern="[a-zA-Z]{1,15}" title="Please enter alphabets only" required>
 <p>Date of Birth</p>
  <input type="date" name="dob" placeholder="Enter your dob" max="<%= maxDate %>" required>
<p>Address</p>
<input type="text" name="addr" placeholder="Enter your address" required>

<p>Contact No</p>
<input type="tel" name="tel" placeholder="Enter contact number" pattern="[0-9]{10}" title="Please enter a 10 digit number" required>

<p>Email</p>
<input type="email" name="email" placeholder="someone@example.com"  required>

<p>Educational Qualification</p>
<select name="edu" required>
<option value="Bachelor's Degree">Bachelor's Degree</option>
<option value="Master's Degree">Master's Degree</option>                         
 </select>
<br><br>
<p>Occupation</p>
<select name="occup" required>
                            <option value="Private Employee">Private Employee</option>
                            <option value="Government Employee">Government Employee</option>
                            <option value="Self Employed">Self Employed</option>
                            <option value="Student">Student</option>
                            <option value="Retired">Retired</option>
                      </select>
<br><br>
<p>Gender</p>
<select name="gen" required>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                      </select>

<p><br>Hint Question</p>
<input type="text" name="hques" placeholder="Security Question" required>

<p>Hint Answer</p>
<input type="text" name="hans" placeholder="Type your answer" required>


            <input type="submit"  value="Register">
         
           
        </form>
        <a href="/PVMS/html/index.html">Back</a>
        
    </div>

<div id="output"></div>
</body>
</head>
</html>