<%@page import="model.Hclass"%>
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
	font-size: 152%;
}

.navbar {
	text-align: center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Home</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">View<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="GetCourses?action=GetAllCourses">All Courses</a></li>
       	 	<li><a href="GetCourses?action=GetCurrentClass">All Classes</a></li>
       		<li><a href="GetCourses?action=GetCurrentClassSubject">All Classes by Subject</a></li>
       		<li><a href="GetCourses?action=GetCurrentClassInstructor">All Classes by Instructor</a></li>
        	<li><a href="SelectTime.jsp">All Classes by Time</a></li>
        	<li><a href="GetCourses?action=GetCourseDepartment">All Courses by Department</a></li>
        	<li><a href="GetCourses?action=GetClassDepartment">All Classes by Department</a></li>
        	<li><a href="GetCourses?action=GetMajorDepartment">All Majors by Department</a></li>
          </ul>
        </li>
        <%
			if(request.getSession().getAttribute("usertype") == null) {
		%>
        		<li><a href="#">Login</a></li>
				<li><a href="#">Sign up</a></li>
        <%
			}else if(request.getSession().getAttribute("usertype") =="student"){
		%>
        		<li><a href="GetDepartmentClass">Enroll</a></li>
        		<li><a href="GetCurrentSchedule">My Schedule</a></li>
        		<li><a href="GetStudentTranscript">My Transcript</a></li>
        <%
			}else if(request.getSession().getAttribute("usertype") =="instructor"){
		%>
				<li><a href="InstructorFirstPage">My Classes</a></li>
        
        
        <%
			}else if(request.getSession().getAttribute("usertype") =="advisor"){
		%>
       			<li><a href="GetStudentInfo">Student Info</a></li>
        
        <%
			}else if(request.getSession().getAttribute("usertype") =="admin"){
		%>
        
        
        <%
			}
		%>
        
      </ul>
    </div>
  </div>
</nav>



</body>
</html>