<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Welcome to Himanshu's Shopping Cart</title>
</head>
<body  style="background-image:url(back.jpg);  background-position:50% -20%; background-repeat:no-repeat;">
<jsp:include page="/navbar.jsp"/>
<div align="center">
<form style="width:40%;" action="SignIn" id="myform">

  <div class="form-group">
    <label for="username">UserId</label>
    <input type="text" class="form-control" name="userid" placeholder="userid">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" name="password" placeholder="Password">
  </div>
  <button type="submit" class="btn btn-default">Sign In!</button>
</form></div>
</body>
</html>