<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<form style="width:40%;" action="SignUpOfficial" id="myform">
<div class="form-group" align="center" >
   <label for="sel1"> 	Departments: </label>
		<select  name="departments" id="sel1">
        <c:forEach var="deparment" items="${darray}">
            <option value="${deparment}">
                <c:out value="${deparment}"/>
            </option>
        </c:forEach>
        </select></div>
  <div class="form-group">
  </div>
  <button type="submit" class="btn btn-default">SignUp</button>
</form>
</body>
</html>