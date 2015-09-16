<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List</title>
</head>
<body>

<form name="1" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetAllCourses">
<input type="submit" value="View All Courses">
</form>

<form name="2" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetCurrentClass">
<input type="submit" value="View Current Classes">
</form>

<form name="3" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetCurrentClassSubject">
<input type="submit" value="View Classes by Subject">
</form>

<form name="4" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetCurrentClassInstructor">
<input type="submit" value="View Classes by Instructor">
</form>

<form name="5" action="SelectTime.jsp" method="get">
<input type="submit" value="View Classes by Time">
</form>

<form name="6" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetCourseDepartment">
<input type="submit" value="View Courses by Dept">
</form>

<form name="7" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetClassDepartment">
<input type="submit" value="View Current Class by Dept">
</form>

<form name="8" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetMajorDepartment">
<input type="submit" value="View Majors by Dept">
</form>



</html>