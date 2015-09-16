<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<form style="width:40%; align:center" action="GetGradeSheetForSemester" id="myform">
<div class="form-control" align="center" >
    <label for="text"> Semester:</label>
     	<select  name="Semester">
        <c:forEach var="semester" items="${Semesters}">
            <option value="${semester}">
                <c:out value="${semester}"/>
            </option>
        </c:forEach>
    </select>
    <label for="text"> 	Year:</label>
     	<select  name="Year">
        <c:forEach var="year" items="${Years}">
            <option value="${year}">
                <c:out value="${year}"/>
            </option>
        </c:forEach>
    </select>
    </div>
<button type="submit" class="btn btn-default">Get Grade Sheet</button>
</form>
</body>
</html>