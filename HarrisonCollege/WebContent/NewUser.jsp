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
<body>
<jsp:include page="/navbar.jsp"/>
<div align="center">
<form style="width:40%;" action="SignUp" id="myform">
<div class="form-group" >
    <label for="name">Full Name</label>
    <input type="text" class="form-control" name="name" placeholder="Full Name">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" name="password" placeholder="Password">
  </div>
  <div class="form-group">
  <label for="permissions">User Type</label>
  <select class="form-control" name="permissions">
    <option>Student</option>
    <option>Instructor</option>
    <option>Advisor</option>
    <option>Admin</option>
    <option>Intructor_Advisor</option>
  </select>
  </div>
  <button type="submit" class="btn btn-default">SignUp</button>
</form>
</div>
</body>
</html>