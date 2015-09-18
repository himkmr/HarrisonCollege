
<nav class="navbar navbar-default" >
  <div class="container-fluid"> <!-- style= "margin-left: 5%;"  --> 
   <ul class="nav navbar-nav">
    <li ><a href="index.jsp"><img src="HarrisonCollege.png" width =50 height = 50></a></li>
     <li style="margin-top:10px;"><a href="index.jsp">Home</a></li>
        <li class="dropdown" style="margin-top:10px;"><a class="dropdown-toggle" data-toggle="dropdown" href="#">View<span class="caret"></span></a>
          <ul class="dropdown-menu" style="margin-top:10px;" >
            <li ><a href="GetCourses?action=GetAllCourses">All Courses</a></li>
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
        		<li style="margin-top:10px;"><a href="SignIn.jsp">Login</a></li>
				<li style="margin-top:10px;"><a href="NewUser.jsp">Sign up</a></li>
        <%
			}else if(((String)request.getSession().getAttribute("usertype")).equalsIgnoreCase("Student")){
		%>
        		<li style="margin-top:10px;"><a href="GetDepartmentClass">Enroll</a></li>
        		<li style="margin-top:10px;"><a href="GetCurrentSchedule">My Schedule</a></li>
        		<li style="margin-top:10px;"><a href="GetStudentTranscript">My Transcript</a></li>
        		<li style="margin-top:10px;"><a href="SignOut">Sign Out</a></li>
        <%
			}else if(((String)request.getSession().getAttribute("usertype")).equalsIgnoreCase("Instructor")){
		%>
				<li style="margin-top:10px;"><a href="InstructorFirstPage">My Classes</a></li>
				<li style="margin-top:10px;"><a href="SignOut">Sign Out</a></li>
        
        
        <%
			}else if(((String)request.getSession().getAttribute("usertype")).equalsIgnoreCase("Advisor")){
		%>
       			<li style="margin-top:10px;"><a href="GetStudentInfo">Student Info</a></li>
       			<li style="margin-top:10px;"><a href="SignOut">Sign Out</a></li>
        
        <%
			}else if(((String)request.getSession().getAttribute("usertype")).equalsIgnoreCase("Admin")){
		%>
		 <li class="dropdown" style="margin-top:10px;"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Search<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="AdminSearch?select=student">Students</a></li>
       	 	<li><a href="AdminSearch?select=instructor">Instructors</a></li>
       		<li><a href="AdminSearch?select=advisor">Advisors</a></li>
       		<li><a href="AdminSearch?select=department">Departments</a></li>
        	<li><a href="AdminSearch?select=course">Courses</a></li>
        	<li><a href="AdminSearch?select=major">Majors</a></li>
        	<li><a href="AdminSearch?select=class">Classes</a></li>
        	<li><a href="AdminSearch?select=classroom">Classrooms</a></li>
          </ul>
        </li>
		<li style="margin-top:10px;"><a href="AdminPendingRequest">Pending Approvals</a></li>
		<li style="margin-top:10px;"><a href="AdminGetRevenue">Manage/ View Revenue</a></li>
        	<li style="margin-top:10px;"><a href="SignOut">Sign Out</a></li>
        
        <%
			}
		%>
        
      </ul>
    </div>
</nav>
