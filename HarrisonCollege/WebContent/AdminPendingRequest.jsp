<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/header.jsp"/>
<title>Pending Admissions</title>
</head>
<body>
<jsp:include page="/navbar.jsp"/>
<div class="container">
  <h2>Pending Admissions</h2>
  <ul class="list-group">
   ${pending}
   ${alert}
  </ul>
</div>

</body>
</html>