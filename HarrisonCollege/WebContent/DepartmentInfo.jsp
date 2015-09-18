<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="/header.jsp" />
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/navbar.jsp" />
	<div class="container">
		<h2>Department</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Code</th>
					<th>Name</th>
				</tr>
			</thead>
			<tbody>${deptInfo}
			</tbody>
		</table>
	</div>
	<div class="container">
		<h2>Courses</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Subject</th>
					<th>Credit Hours</th>
				</tr>
			</thead>
			<tbody>${courses}
			</tbody>
		</table>
	</div>

	<div class="container">
		<h2>Current Classes</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Subject</th>
					<th>Day</th>
					<th>Start Time</th>
					<th>End Time</th>
					<th>Semester</th>
					<th>Year</th>
				</tr>
			</thead>
			<tbody>${currentClasses}
			</tbody>
		</table>
	</div>

	<div class="container">
		<h2>Majors</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name</th>
					<th>Department</th>
				</tr>
			</thead>
			<tbody>${majors}
			</tbody>
		</table>
	</div>
</body>
</html>