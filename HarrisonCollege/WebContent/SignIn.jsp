<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header.jsp"/>
<title>Welcome to Harrison College</title>
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