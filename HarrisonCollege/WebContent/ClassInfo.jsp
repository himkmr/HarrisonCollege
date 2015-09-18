<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/header.jsp" />
<title>ClassInfo</title>
</head>
<body>
	<jsp:include page="/navbar.jsp" />
	<div class="container">
		<h2>Class</h2>
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
			<tbody>${classInfo}
			</tbody>
		</table>
	</div>
	<%
		if (!(Boolean) request.getAttribute("hasInstructor")) {
	%>
	<div class="container">
		<form class="form-horizontal" role="form" method="post"
			action="ClassInfo">
			<div class="form-group">
				<label for="selInstructor">Select Instructor:</label> <select
					class="form-control" id="selInstructor" name="selInstructor">
					${instructorList}
				</select>
				 <input type="hidden" name="classId" value="${classId}"/>
				 <button type="submit" class="btn btn-default">Submit</button>
			</div>
		</form>
	</div>
	<%
		}
	%>
	<div class="container">
		<h2>Instructor</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Department</th>
					<th>Office #</th>
				</tr>
			</thead>
			<tbody>${instructor}
			</tbody>
		</table>
	</div>

	<div class="container">
		<h2>Students</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Entry Year</th>
				</tr>
			</thead>
			<tbody>${students}
			</tbody>
		</table>
	</div>
</body>
</html>