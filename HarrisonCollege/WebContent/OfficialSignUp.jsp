<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/navbar.jsp"/>
<div align="center">
<form id="bootstrapSelectForm"  style="width:40%"  class="form-horizontal" action="SignUpOfficial">
<div class="form-group">
   <label class="col-xs-3 control-label"> 	Select Department: </label>
     <div class="col-xs-5 selectContainer">
		<select  name="departments" class="form-control">
        <c:forEach var="deparment" items="${darray}">
            <option value="${deparment}">
                <c:out value="${deparment}"/>
            </option>
        </c:forEach>
        </select></div></div>
  <button type="submit" class="btn btn-default">Submit Request</button>
</form></div>
</body>
</html>