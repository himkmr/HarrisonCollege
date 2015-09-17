<%@page import="model.Hclass"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset ="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<nav class="navbar navbar-inverse" style= "height: 28px;font-size:0.8em;">
  <div class="container-fluid" style= "margin-left: 5%;"  >
    <div class="navbar-header">
      <a class="navbar-brand" href="index.jsp">Home</a>
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
        		<li><a href="SignIn.jsp">Login</a></li>
				<li><a href="NewUser.jsp">Sign up</a></li>
        <%
			}else if(((String)request.getSession().getAttribute("usertype")).equalsIgnoreCase("Student")){
		%>
        		<li><a href="GetDepartmentClass">Enroll</a></li>
        		<li><a href="GetCurrentSchedule">My Schedule</a></li>
        		<li><a href="GetStudentTranscript">My Transcript</a></li>
        		<li><a href="SignOut">Sign Out</a></li>
        <%
			}else if(((String)request.getSession().getAttribute("usertype")).equalsIgnoreCase("Instructor")){
		%>
				<li><a href="InstructorFirstPage">My Classes</a></li>
				<li><a href="SignOut">Sign Out</a></li>
        
        
        <%
			}else if(((String)request.getSession().getAttribute("usertype")).equalsIgnoreCase("Advisor")){
		%>
       			<li><a href="GetStudentInfo">Student Info</a></li>
       			<li><a href="SignOut">Sign Out</a></li>
        
        <%
			}else if(((String)request.getSession().getAttribute("usertype")).equalsIgnoreCase("Admin")){
		%>
		<li><a href="AdminSearch">Search</a></li>
		<li><a href="AdminPendingRequest">Pending Approvals</a></li>
		<li><a href="AdminGetRevenue">Manage/ View Revenue</a></li>
        	<li><a href="SignOut">Sign Out</a></li>
        
        <%
			}
		%>
        
      </ul>
    </div>
  </div>
</nav>



</body>
</html>