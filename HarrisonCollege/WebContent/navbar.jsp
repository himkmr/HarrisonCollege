<nav class="navbar navbar-inverse">
<div class="container">
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
<ul class="nav navbar-nav">
<li><a href="#"></a></li>
<li><a href="#"></a></li>
<li><a href="#"></a></li>
<li><a href="index.jsp"><b>HOME</b></a></li>
<li><a href="NewUser.jsp"><b>Sign Up</b></a></li>
<li><a href="SignIn.jsp"><b>Sign In</b></a></li>
 <ul class="dropdown-menu">
<li><form name="1" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetAllCourses">
<input type="submit" value="View All Courses">
</form>
</li><li>
<form name="2" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetCurrentClass">
<input type="submit" value="View Current Classes">
</form>
</li><li>
<form name="3" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetCurrentClassSubject">
<input type="submit" value="View Classes by Subject">
</form>
</li><li>
<form name="4" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetCurrentClassInstructor">
<input type="submit" value="View Classes by Instructor">
</form>
</li><li>
<form name="5" action="SelectTime.jsp" method="get">
<input type="submit" value="View Classes by Time">
</form>
</li><li>
<form name="6" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetCourseDepartment">
<input type="submit" value="View Courses by Dept">
</form>
</li><li>
<form name="7" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetClassDepartment">
<input type="submit" value="View Current Class by Dept">
</form></li><li>
<form name="8" action="GetCourses" method="get">
<input type="hidden" name="action" value="GetMajorDepartment">
<input type="submit" value="View Majors by Dept">
</form></li></ul></ul>
</div>
</div>
</nav>