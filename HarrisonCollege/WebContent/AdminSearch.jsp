<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/header.jsp" />
<title>Search</title>
</head>
<body>
	<jsp:include page="/navbar.jsp" />

	<div class="container" s>
		<form role="form" method="POST" action="AdminSearch">
			<div class="form-group">
				<label for="selct">Select:</label> <select class="form-control"
					id="select" name ="select">
					<option value="students">Students</option>
					<option value="instructors">Instructors</option>
					<option value="advisors">Advisors</option>
					<option value="departments">Departments</option>
					<option value="courses">Courses</option>
					<option value="majors">Majors</option>
					<option value="classes">Classes</option>
					<option value="classrooms">Classrooms</option>
				</select>
				<input type="submit" class="btn btn-info">
			</div>
		</form>
	</div>
	${display}
	
	
</body>
<script type="text/javascript">
jQuery(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.document.location = $(this).data("href");
    });
});
</script>
</html>

