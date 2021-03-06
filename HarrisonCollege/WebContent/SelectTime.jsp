<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/header.jsp"/>
<title>Select time</title>
</head>
<body>
<jsp:include page="/navbar.jsp"/>
	<div class="container">
		<form role="form" method="GET" action="GetClassTime">
			<div class="form-group">
				<label for="selectedTime">Please select the start time of a class:</label> 
			</div>
			<div class="form-group">
				<select name="selectedTime" class="form-control">
					<option value="0800">8:00</option>
					<option value="0900">9:00</option>
					<option value="1000">10:00</option>
					<option value="1100">11:00</option>
					<option value="1200">12:00</option>
					<option value="1300">13:00</option>
					<option value="1400">14:00</option>
					<option value="1500">15:00</option>
					<option value="1600">16:00</option>
					<option value="1700">17:00</option>
					<option value="1800">18:00</option>
					<option value="1900">19:00</option>
					<option value="2000">20:00</option>
				</select>
			</div>
			<button type="submit" class="btn btn-default">Search</button>
		</form>
	</div>
</body>
</html>