<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
li {
    font-size: 200%;
  
}
.navbar-default {
    background-color: #0A4600;
    border-color: #E7E7E7;
}
</style>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div>
      <ul class="nav navbar-nav">

        <li><a href="index.jsp"><img src="HarrisonCollege.png" width =80 height = 80></a></li>
        <li><a href="list">Products</a></li>
       
        <%if(request.getSession().getAttribute("username")==null){%>
        	<li><a href="login.jsp"><img src="" width =0 height = 80>Login</a></li>
        	<li><a href="CreateAccount.jsp">Create Account</a></li>
        <%}else{%>
            <li><a href="viewForSale">Your Products </a></li>
              <li><a href="ViewCartServlet">Cart</a></li>
              <li><a href="ViewPurchases">Purchased Items</a></li>
              <li><a href="logout">Logout</a></li>
 
              
        <%} %>
<li style="font-size: 120%;"><form class="form-inline" action="Search">
		<input type="text" name  ="search">
		<input type="submit" value="Search">
		</form></li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>