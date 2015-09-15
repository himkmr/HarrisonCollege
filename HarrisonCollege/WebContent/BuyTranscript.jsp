<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buy Transcript</title>
</head>
<body>
<div class="container">
  <form role="form" method="POST" action="OrderSummary.jsp">
    <div class="form-group">
      <label for="firstname">First Name:</label>
      <input type="text" class="form-control" name="firstname" placeholder="Enter first name" required>
    </div>
    <div class="form-group">
      <label for="lastname">Last Name:</label>
      <input type="text" class="form-control" name="lastname" placeholder="Enter last name" required>
    </div>
    <div class="form-group">
      <label for="address">Address:</label>
      <input type="text" class="form-control" name="address" placeholder="Enter address" required>
    </div>
    <div class="form-group">
      <label for="card">Card #:</label>
      <input type="text" class="form-control" name="card" placeholder="Enter card #" required>
    </div>
    <div class="form-group">
      <label for="copies">Copies:</label>
      <select type="number" name="copies" class="form-control" required>
  		<option value="1">1</option>
  		<option value="2">2</option>
  		<option value="3">3</option>
  		<option value="4">4</option>
  		<option value="5">5</option>
	</select>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>
</body>
</html>