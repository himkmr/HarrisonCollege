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
<form id="bootstrapSelectForm"  style="width:40%"  class="form-horizontal" action="CalculateRevenue" >
    <div class="form-group">
        <label class="col-xs-3 control-label">Department</label>
        <div class="col-xs-5 selectContainer">
            <select name="departments" class="form-control" >
			 <c:forEach var="department" items="${departments}">
            	<option value="${department}">
                <c:out value="${department}"/>
            </option>
        </c:forEach>
    </select></div></div>
    <div class="form-group">
        <label class="col-xs-3 control-label">Semester</label>
        <div class="col-xs-5 selectContainer">
            <select name="semesters" class="form-control" >
        <c:forEach var="semester" items="${semesters}">
            <option value="${semester}">
                <c:out value="${semester}"/>
            </option>
        </c:forEach>
    </select></div></div>
    
  <div class="form-group">
        <label class="col-xs-3 control-label">Year</label>
        <div class="col-xs-5 selectContainer">
         <select name="years" class="form-control" >
        <c:forEach var="year" items="${years}">
            <option value="${year}">
                <c:out value="${year}"/>
            </option>
        </c:forEach>
    </select></div></div>
   <div class="form_control" align="center" >
<button type="submit" class="btn btn-default">Get Revenue</button></div>
</form></div>
<br>
<div align="center">
<form id="bootstrapSelectForm"  style="width:40%"  class="form-horizontal" action="ChangeFee" >
    <div class="form-group">
        <label class="col-xs-3 control-label">Semester</label>
        <div class="col-xs-5 selectContainer">
            <select name="changesemester" class="form-control" >
        <c:forEach var="semester" items="${semesters}">
            <option value="${semester}">
                <c:out value="${semester}"/>
            </option>
        </c:forEach>
    </select></div></div>

    <div class="form-group" >
     <label class="col-xs-3 control-label"> Year:</label>
         <div class="col-xs-5 selectContainer">
    	<select  name="changeyear" class="form-control" >
        <c:forEach var="year" items="${comingyears}">
            <option value="${year}">
                <c:out value="${year}"/>
            </option>
        </c:forEach>
    </select></div></div>
    <div class="form-group">
     <label class="col-xs-3 control-label"> Enter New Fee</label>
      <div class="col-xs-5 inputcontainer">
     <input name="fee" type="text"></div></div>
   <div class="form_control" align="center" >
<button type="submit" class="btn btn-default">Update Fee</button></div>
</form></div>

</body>
</html>