<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/navbar.jsp"/>
<div class="container">
<form  class="form-horizontal"  action="GetGradeSheet" id="myform">
<div class="form-group" align="center" >
   <label  for="sel1"> 	Semester: </label>
		<select  name="semesters" id="sel1">
        <c:forEach var="semester" items="${sarray}">
            <option value="${semester}">
                <c:out value="${semester}"/>
            </option>
        </c:forEach>
        </select>  </div><div  class="form-group" align="center">
        <label for="sel1"> 	Year: </label>
		<select  name="years" id="sel1">
        <c:forEach var="year" items="${yarray}">
            <option value="${year}">
                <c:out value="${year}"/>
            </option>
        </c:forEach>
        </select>      
        </div>
  <div class="form-group"  align="center">
  <button type="submit" class="btn btn-default">Get Grade Sheet</button>  </div>
</form></div>
</body>
</html>