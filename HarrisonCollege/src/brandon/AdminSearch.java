package brandon;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;
import model.*;

/**
 * Servlet implementation class AdminSearch
 */
@WebServlet("/AdminSearch")
public class AdminSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String criteria = (String) request.getAttribute("select");
		String display="";
		System.out.println("CRITERIA " + criteria);
		if(criteria!=null){
		display = displaySearchList(criteria);
		}
		request.setAttribute("display", display);
		getServletContext().getRequestDispatcher("/AdminSearch.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String criteria = request.getParameter("select");
		String display="";
		System.out.println(criteria);
		if (departmentFormAvailable(request)) {
			Admin.createDepartment(request.getParameter("code"),
					request.getParameter("name"));
		} else if (courseFormAvailable(request)) {
			Hdepartment department = DBUtil.find(
					Long.parseLong(request.getParameter("selDepartment")),
					Hdepartment.class);
			System.out.println(department.getName());
			Admin.createCourse(department, request.getParameter("subject"),
					Integer.parseInt(request.getParameter("hours")));
		} else if (majorFormAvailable(request)) {
			Hdepartment department = DBUtil.find(
					Long.parseLong(request.getParameter("selDepartment")),
					Hdepartment.class);
			System.out.println(department.getName());
			Admin.createMajor(department, request.getParameter("name"));
		} else if (classFormAvailable(request)) {
			Hclassroom classroom = DBUtil.find(
					Long.parseLong(request.getParameter("selClassroom")),
					Hclassroom.class);
			Hcours course = DBUtil.find(
					Long.parseLong(request.getParameter("selCourse")),
					Hcours.class);
			System.out.println(request.getParameter("selDay"));
			Admin.createClass(course, printDays(request), classroom,
					request.getParameter("startTime"),
					request.getParameter("endTime"),
					request.getParameter("selSemester"),
					request.getParameter("year"));
		} else if (classroomFormAvailable(request)) {
			Admin.createClassroom(request.getParameter("building"),
					Integer.parseInt(request.getParameter("capacity")),
					Integer.parseInt(request.getParameter("roomNumber")));
		}
		if(criteria!=null){
		display = displaySearchList(criteria);
		}
		request.setAttribute("display", display);

		getServletContext().getRequestDispatcher("/AdminSearch.jsp").forward(request, response);
	}

	protected static String displaySearchList(String criteria) {
		StringBuilder display = new StringBuilder();
		switch (criteria) {
		case ("students"):

			display.append(displayStudents(Admin.getAllStudents()));
			break;
		case ("instructors"):
			display.append(displayInstructors(Admin.getAllInstructors()));
			break;
		case ("advisors"):
			display.append(displayAdvisors(Admin.getAllAdvisors()));
			break;
		case ("departments"):
			display.append(departmentCreationForm());
			display.append(displayDepartments(Admin.getAllDepartments()));
			break;
		case ("courses"):
			display.append(courseCreationForm());
			display.append(displayCourses(Admin.getAllCourses()));
			break;
		case ("majors"):
			display.append(majorCreationForm());
			display.append(displayMajors(Admin.getAllMajors()));
			break;
		case ("classes"):
			display.append(classCreationForm());
			display.append(displayClasses(Admin.getAllClasses()));
			break;
		case ("classrooms"):
			display.append(classroomCreationForm());
			display.append(displayClassrooms(Admin.getAllClassrooms()));
			break;
		default:
			return "";
		}
		return display.toString();
	}

	protected static String displayStudents(List<Hstudent> students) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Students</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Name</th><th>Entry Year</th></tr></thead><tbody>");
		for (Hstudent s : students) {
			display.append("<tr class='clickable-row' data-href= \"StudentInfo?id="
					+ s.getStudentId()
					+ "\"><td>"
					+ s.getStudentId()
					+ "</td><td>"
					+ s.getHuser().getName()
					+ "</td><td>"
					+ s.getEntryYear()

					+ "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected static String displayInstructors(List<Hofficial> instructors) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Instructors</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Name</th><th>Department</th> <th>Office #</th></tr></thead><tbody>");

		for (Hofficial i : instructors) {
			display.append("<tr class='clickable-row' data-href= \"OfficialInfo?id="
					+ i.getOfficialId()
					+ "\"><td>"
					+ i.getOfficialId()
					+ "</td><td>"
					+ i.getHuser().getName()
					+ "</td><td>"
					+ i.getHdepartment().getName()
					+ "</td><td>"
					+ i.getOfficeNumber() + "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected static String displayAdvisors(List<Hofficial> advisors) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Advisors</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Name</th><th>Department</th> <th>Office #</th></tr></thead><tbody>");

		for (Hofficial a : advisors) {
			display.append("<tr class='clickable-row' data-href= \"AdminCreate.jsp\"><td>"
					+ a.getOfficialId()
					+ "</td><td>"
					+ a.getHuser().getName()
					+ "</td><td>"
					+ a.getHdepartment().getName()
					+ "</td><td>"
					+ a.getOfficeNumber() + "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected static String displayDepartments(List<Hdepartment> departments) {
		StringBuilder display = new StringBuilder();

		display.append("<div class=\"container\"><h2>Departments</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Code</th><th>Name</th></tr></thead><tbody>");
		for (Hdepartment d : departments) {

			if (d.getEnabled().equals("yes")) {
				display.append("<tr class='clickable-row' data-href= \"AdminCreate.jsp\">");
				display.append("<td>"
						+ d.getCode()
						+ "</td><td>"
						+ d.getName()
						+ "</td><td>"
						+ "<a href=\"Enable?todo=enable&type=department&id="
						+ d.getDepartmentId()
						+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
						+ "<a href=\"Enable?todo=disable&type=department&id="
						+ d.getDepartmentId()
						+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
						+ "</td></tr>");
			} else {
				display.append("<tr class='clickable-row' data-href= \"AdminCreate.jsp\" style=\"color: #fff; background: black;\">");
				display.append("<td>"
						+ d.getCode()
						+ "</td><td>"
						+ d.getName()
						+ "</td><td>"
						+ "<a href=\"Enable?todo=enable&type=department&id="
						+ d.getDepartmentId()
						+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
						+ "<a href=\"Enable?todo=disable&type=department&id="
						+ d.getDepartmentId()
						+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
						+ "</td></tr>");
			}
		}
			display.append("</tbody></table></div>");
			return display.toString();
	}

	protected static String displayCourses(List<Hcours> courses) {
		StringBuilder display = new StringBuilder();

		display.append("<div class=\"container\"><h2>Courses</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Subject</th><th>Credit Hours</th></tr></thead><tbody>");
		for (Hcours c : courses) {
			if(c.getEnabled().equals("yes")){
			display.append("<tr class='clickable-row' data-href= \"AdminCreate.jsp\"><td>"
					+ c.getSubject()
					+ "</td><td>"
					+ c.getCreditHours()
					+ "</td><td>"
					+ "<a href=\"Enable?todo=enable&type=course&id="
					+ c.getCourseId()
					+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
					+ "<a href=\"Enable?todo=disable&type=course&id="
					+ c.getCourseId()
					+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
					+ "</td></tr>");
			}
			else{
				display.append("<tr class='clickable-row' data-href= \"AdminCreate.jsp\" style=\"color: #fff; background: black;\"><td>"
						+ c.getSubject()
						+ "</td><td>"
						+ c.getCreditHours()
						+ "</td><td>"
						+ "<a href=\"Enable?todo=enable&type=course&id="
						+ c.getCourseId()
						+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
						+ "<a href=\"Enable?todo=disable&type=course&id="
						+ c.getCourseId()
						+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
						+ "</td></tr>");
			}
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected static String displayMajors(List<Hmajor> majors) {
		StringBuilder display = new StringBuilder();

		display.append("<div class=\"container\"><h2>Majors</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Name</th><th>Department</th></tr></thead><tbody>");
		for (Hmajor m : majors) {
			if(m.getEnabled().equals("yes")){
			display.append("<tr nclass='clickable-row' data-href= \"AdminCreate.jsp\"><td>"
					+ m.getName()
					+ "</td><td>"
					+ m.getHdepartment().getName()
					+ "</td><td>"
					+ "<a href=\"Enable?todo=enable&type=major&id="
					+ m.getMajorId()
					+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
					+ "<a href=\"Enable?todo=disable&type=major&id="
					+ m.getMajorId()
					+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
					+ "</td></tr>");
			}
			else{
				display.append("<tr class='clickable-row' data-href= \"AdminCreate.jsp\" style=\"color: #fff; background: black;\"><td>"
						+ m.getName()
						+ "</td><td>"
						+ m.getHdepartment().getName()
						+ "</td><td>"
						+ "<a href=\"Enable?todo=enable&type=major&id="
						+ m.getMajorId()
						+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
						+ "<a href=\"Enable?todo=disable&type=major&id="
						+ m.getMajorId()
						+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
						+ "</td></tr>");
			}
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected static String displayClasses(List<Hclass> classes) {
		StringBuilder display = new StringBuilder();

		display.append("<div class=\"container\"><h2>Classes</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Subject</th><th>Day</th>"
				+ "<th>Start Time</th><th>End Time</th><th>Semester</th><th>Year</th></tr></thead><tbody>");
		for (Hclass c : classes) {
			if(c.getEnabled().equals("yes")){
				display.append("<tr class='clickable-row' data-href= \"AdminCreate.jsp\"><td>"
					+ c.getHcours().getCourseId()
					+ "</td><td>"
					+ c.getHcours().getSubject()
					+ "</td><td>"
					+ c.getDay()
					+ "</td><td>"
					+ c.getStarttime()
					+ "</td><td>"
					+ c.getEndtime()
					+ "</td><td>"
					+ c.getSemester()
					+ "</td><td>"
					+ c.getYear()
					+ "</td><td>"
					+ "<a href=\"Enable?todo=enable&type=class&id="
					+ c.getClassId()
					+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
					+ "<a href=\"Enable?todo=disable&type=class&id="
					+ c.getClassId()
					+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
					+ "</td></tr>");
			}
			else{
				display.append("<tr class='clickable-row' data-href= \"AdminCreate.jsp\" style=\"color: #fff; background: black;\"><td>"
					+ c.getHcours().getCourseId()
					+ "</td><td>"
					+ c.getHcours().getSubject()
					+ "</td><td>"
					+ c.getDay()
					+ "</td><td>"
					+ c.getStarttime()
					+ "</td><td>"
					+ c.getEndtime()
					+ "</td><td>"
					+ c.getSemester()
					+ "</td><td>"
					+ c.getYear()
					+ "</td><td>"
					+ "<a href=\"Enable?todo=enable&type=class&id="
					+ c.getClassId()
					+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
					+ "<a href=\"Enable?todo=disable&type=class&id="
					+ c.getClassId()
					+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
					+ "</td></tr>");
				
			}
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected static String displayClassrooms(List<Hclassroom> classrooms) {
		StringBuilder display = new StringBuilder();

		display.append("<div class=\"container\"><h2>Classrooms</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Room Number</th><th>Building</th><th>Capacity</th></tr></thead><tbody>");
		for (Hclassroom c : classrooms) {
			if(c.getEnabled().equals("yes")){
			display.append("<tr class='clickable-row' data-href= \"AdminCreate.jsp\"><td>"
					+ c.getRoomNumber()
					+ "</td><td>"
					+ c.getBuilding()
					+ "</td><td>"
					+ c.getCapacity()
					+ "</td><td>"
					+ "<a href=\"Enable?todo=enable&type=classroom&id="
					+ c.getClassroomId()
					+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
					+ "<a href=\"Enable?todo=disable&type=classroom&id="
					+ c.getClassroomId()
					+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
					+ "</td></tr>");
			}
			else{
				display.append("<tr class='clickable-row' data-href= \"AdminCreate.jsp\" style=\"color: #fff; background: black;\"><td>"
						+ c.getRoomNumber()
						+ "</td><td>"
						+ c.getBuilding()
						+ "</td><td>"
						+ c.getCapacity()
						+ "</td><td>"
						+ "<a href=\"Enable?todo=enable&type=classroom&id="
						+ c.getClassroomId()
						+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
						+ "<a href=\"Enable?todo=disable&type=classroom&id="
						+ c.getClassroomId()
						+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
						+ "</td></tr>");
			}
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected static String departmentCreationForm() {
		return "<div class=\"container\"><form class=\"form-inline\" role=\"form\" method=\"post\" action=\"AdminSearch\">"
				+ "<div class=\"form-group\"><label for=\"code\">Code:</label><input type=\"text\" class=\"form-control\""
				+ " id=\"code\" name =\"code\" placeholder=\"Enter Code\"></div><div class=\"form-group\"><label for=\"name\">Name:</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"name\" name =\"name\" placeholder=\"Enter Name\"></div>"
				+ "<input type=\"hidden\" name=\"select\" value=\"departments\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Add</button></form></div>";

	}

	protected static String courseCreationForm() {
		return "<div class=\"container\"><form class=\"form-inline\" role=\"form\" method=\"post\" action=\"AdminSearch\">"
				+ "<div class=\"form-group\"><label for=\"subject\">Subject</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"subject\" name =\"subject\" placeholder=\"Enter Subject\"></div>"
				+ "<div class=\"form-group\"><label for=\"hours\">Credit Hours:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"hours\" name =\"hours\" placeholder=\"Enter Credit Hours\"></div>"
				+ "<div class=\"form-group\"><label for=\"selDepartment\">Select Department:</label>"
				+ "<select class=\"form-control\" id=\"selDepartment\" name =\"selDepartment\">"
				+ listDepartments()
				+ "</select></div>"
				+ "<input type=\"hidden\" name=\"select\" value=\"courses\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Add</button></form></div>";

	}

	protected static String majorCreationForm() {
		return "<div class=\"container\"><form class=\"form-inline\" role=\"form\" method=\"post\" action=\"AdminSearch\">"
				+ "<div class=\"form-group\"><label for=\"name\">Name</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"name\" name =\"name\" placeholder=\"Enter Name\"></div>"
				+ "<div class=\"form-group\"><label for=\"selDepartment\">Select Department:</label>"
				+ "<select class=\"form-control\" id=\"selDepartment\" name =\"selDepartment\">"
				+ listDepartments()
				+ "</select></div>"
				+ "<input type=\"hidden\" name=\"select\" value=\"majors\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Add</button></form></div>";

	}

	protected static String classCreationForm() {
		return "<div class=\"container\"><form class=\"form-inline\" role=\"form\" method=\"post\" action=\"AdminSearch\">"
				+ "<div class=\"form-group\"><label for=\"selCours\">Select Course:</label>"
				+ "<select class=\"form-control\" id=\"selCourse\" name =\"selCourse\">"
				+ listCourses()
				+ "</select></div>"

				+ "<div class=\"form-group\"><label for=\"selClassroom\">Select Classroom:</label>"
				+ "<select class=\"form-control\" id=\"selClassroom\" name =\"selClassroom\">"
				+ listClassrooms()
				+ "</select></div>"

				+ "<div class =\"form-group\"><label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"monday\" value=\"M\">M</label>"
				+ "<label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"tuesday\" value=\"T\">T</label><label class=\"checkbox-inline\">"
				+ "<input type=\"checkbox\" name=\"wednesday\" value=\"W\">W</label><label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"thursday\" value=\"H\">H"
				+ "</label><label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"friday\" value=\"F\">F</label></div>"

				+ "<div class=\"form-group\"><label for=\"startTime\">Start Time:</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"startTime\" name =\"startTime\" placeholder=\"Enter Start Time\"></div>"

				+ "<div class=\"form-group\"><label for=\"endTime\">End Time:</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"endTime\" name =\"endTime\" placeholder=\"Enter End Time\"></div>"

				+ "<div class=\"form-group\"><label for=\"selSemester\">Select Semester:</label>"
				+ "<select class=\"form-control\" id=\"selSemester\" name =\"selSemester\">"
				+ "<option value =\"Fall\">Fall</option><option value =\"Spring\">Spring</option>"
				+ "</select></div>"

				+ "<div class=\"form-group\"><label for=\"year\">Year:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"year\" name =\"year\" min =\"2015\" max = \"2070\" value=\"2015\"></div>"

				+ "<input type=\"hidden\" name=\"select\" value=\"classes\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Add</button></form></div>";

	}

	protected static String classroomCreationForm() {
		return "<div class=\"container\"><form class=\"form-inline\" role=\"form\" method=\"post\" action=\"AdminSearch\">"

				+ "<div class=\"form-group\"><label for=\"building\">Building:</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"building\" name =\"building\" placeholder=\"Enter Building\"></div>"

				+ "<div class=\"form-group\"><label for=\"capacity\">Capacity:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"capacity\" name =\"capacity\" min =\"0\" value=\"0\"></div>"

				+ "<div class=\"form-group\"><label for=\"roomNumber\">Room Number:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"roomNumber\" min =\"100\" value=\"100\" name =\"roomNumber\"></div>"

				+ "<input type=\"hidden\" name=\"select\" value=\"classrooms\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Add</button></form></div>";

	}

	protected static String listDepartments() {
		StringBuilder listDepart = new StringBuilder();
		for (Hdepartment d : Admin.getAllDepartments()) {
			;
			listDepart.append("<option value= " + d.getDepartmentId() + ">"
					+ d.getCode() + " " + d.getName() + "</option>");
		}
		return listDepart.toString();
	}

	protected static String listCourses() {
		StringBuilder listCourses = new StringBuilder();
		for (Hcours d : Admin.getAllCourses()) {
			;
			listCourses.append("<option value= " + d.getCourseId() + ">"
					+ d.getSubject() + "</option>");
		}
		return listCourses.toString();
	}

	protected static String listClassrooms() {
		StringBuilder listClassrooms = new StringBuilder();
		for (Hclassroom d : Admin.getAllClassrooms()) {
			;
			listClassrooms.append("<option value= " + d.getClassroomId() + ">"
					+ d.getBuilding() + " " + d.getRoomNumber() + "</option>");
		}
		return listClassrooms.toString();
	}

	protected static boolean departmentFormAvailable(HttpServletRequest request) {
		if (request.getParameter("code") != null
				&& request.getParameter("name") != null) {
			System.out.println("departmentForm is available");
			return true;
		} else
			return false;
	}

	protected static boolean majorFormAvailable(HttpServletRequest request) {
		if (request.getParameter("name") != null
				&& request.getParameter("selDepartment") != null) {
			System.out.println("majorForm is available");
			return true;
		} else
			return false;
	}

	protected static boolean courseFormAvailable(HttpServletRequest request) {
		if (request.getParameter("selDepartment") != null
				&& request.getParameter("subject") != null
				&& request.getParameter("hours") != null) {
			System.out.println("courseForm is available");
			return true;
		} else
			return false;
	}

	protected static boolean classFormAvailable(HttpServletRequest request) {
		if (request.getParameter("selCourse") != null
				&& request.getParameter("selClassroom") != null
				&& request.getParameter("startTime") != null
				&& request.getParameter("endTime") != null
				&& request.getParameter("selSemester") != null
				&& request.getParameter("year") != null) {
			System.out.println("classForm is available");
			return true;
		} else
			return false;
	}

	protected static boolean classroomFormAvailable(HttpServletRequest request) {
		if (request.getParameter("building") != null
				&& request.getParameter("capacity") != null
				&& request.getParameter("roomNumber") != null) {
			System.out.println("classroomForm is available");
			return true;
		} else
			return false;
	}

	protected static String printDays(HttpServletRequest request) {
		StringBuilder days = new StringBuilder();
		if (request.getParameter("monday") != null) {
			days.append("M");
		}
		if (request.getParameter("tuesday") != null) {
			days.append("T");
		}
		if (request.getParameter("wednesday") != null) {
			days.append("W");
		}
		if (request.getParameter("thursday") != null) {
			days.append("H");
		}
		if (request.getParameter("friday") != null) {
			days.append("F");
		}
		return days.toString();
	}

}
