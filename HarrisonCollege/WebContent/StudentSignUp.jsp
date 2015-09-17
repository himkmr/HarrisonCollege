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
<form id="bootstrapSelectForm"  style="width:40%"  class="form-horizontal"  action="SignUpStudent">
<div class="form-group">
    <label class="col-xs-3 control-label"> Select Major</label>
          <div class="col-xs-5 selectContainer">
     	<select  name="majors" class="form-control">
        <c:forEach var="major" items="${marray}">
            <option value="${major}">
                <c:out value="${major}"/>
            </option>
        </c:forEach>
    </select></div></div>
  <div class="form-group">
    <label  class="col-xs-3 control-label">Enrollment Year</label>
     <div class="col-xs-5 inputContainer">
    <input type="text"  name="year">
  </div></div>
<button type="submit" class="btn btn-default">SignUp</button>
</form>
</div>
</body>
</html>