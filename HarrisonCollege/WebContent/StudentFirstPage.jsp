<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  
<title>Welcome to Harrison College</title>
</head>
<body>
<jsp:include page="/navbar.jsp"/>

<div align="center">
Your User ID: ${userid}</div>
</body>

<div class="container">
  <h2>Welcome to Harrison College</h2>
  <br><br>
  <div class="btn-group">
    
    
    
    
    <a class="btn btn-primary" href="#">Enroll</a>
    <a class="btn btn-primary" href="#">Drop Class</a>
    <a class="btn btn-primary" href="GetCurrentSchedule">My Schedule</a>
    <a class="btn btn-primary" href="GetStudentTranscript">My Transcript</a>
    <div class="btn-group">
      <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
      View <span class="caret"></span></button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="GetCourses?action=GetAllCourses">All Courses</a></li>
        <li><a href="GetCourses?action=GetCurrentClass">All Classes</a></li>
        <li><a href="GetCourses?action=GetCurrentClassSubject">All Classes by Subject</a></li>
        <li><a href="GetCourses?action=GetCurrentClassInstructor">All Classes by Instructor</a></li>
        <li><a href="SelectTime.jsp">All Classes by Time</a></li>
        <li><a href="GetCourses?action=GetCourseDepartment">All Courses by Department</a></li>
        <li><a href="GetCourses?action=GetClassDepartment">All Classes by Department</a></li>
        <li><a href="GetCourses?action=GetMajorDepartment">All Majors by Department</a></li>
      </ul>
    </div>
  </div>
</div>





</html>