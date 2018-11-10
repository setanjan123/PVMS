<html>
<%@page import="com.pvms.bean.Transaction,java.time.LocalDate" %>
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
    Transaction t=(Transaction)session.getAttribute("txn");
    if(t==null)
    {
    	response.sendRedirect("/PVMS/jsp/home.jsp");
    	return;
    }
    LocalDate ld=LocalDate.now();
    ld=ld.plusDays(14);
    String minExpDate=ld.getYear()+"-"+ld.getMonthValue();
  %>
  <link rel="stylesheet" href="<%= request.getContextPath()%>/css/bootstrap.min.css">
   <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css">
  <h1>
    <div class="jumbotron text-center header"><b>Payment Information</b></div>
  </h1>
  <style>
  .header
  {
     background-image:url("/PVMS/images/maxresdefault.jpg");
     background-repeat: no-repeat;
     background-size:contain;
  }
  body
   {
     background-image:url("/PVMS//images/bg.jpg");
     background-repeat: no-repeat;
     background-size:cover;
   }
  .body
   { 
      background-color:black;
      color:white;
      height:auto;
      width:500px;
   }
   b
   {
      color:#A20D11;
    }
  </style>
  </head>
  <body>
  <form class="loginbox" action="<%= request.getContextPath()%>/PaymentController" method="post">
    <input type="hidden" name="isValid" value="true">
    <p>Transaction Id: <%= t.getId()  %></p><br>
    <p>Transaction Amount: <%= t.getAmount() %></p><br>
    <p>Transaction Type: <%= t.getType()  %></p><br>
    <p>Card Type:</p>
        <select>
        <option>Credit Card</option>
        <option>Debit Card</option>
        </select>
    <p>Name on Card:</p>
    <input type="text" id="name" pattern="[a-zA-Z]{1,30}\s[a-zA-Z]{1,30}" title="Alphabets Only" required><br>
    <p>Card Number:</p>
     <input type="text"  id="cardno" pattern="[0-9]{16}" title="Numbers only. Needs to be 16 digits" required><br>
    <p>Expires(month and year):</p><input type="month" min="<%=minExpDate %>"required>
    <p>CVV/CVC:</p>
    <input type="text" pattern="[0-9]{3}" title="3 digit number only" required><br>
  <input type="submit"  class="btn btn-success" value="Pay Now">
  </form>
  </body>
</html>