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
		String criteria = request.getParameter("select");
		String display = "";
		System.out.println("CRITERIA " + criteria);
		if (criteria != null) {
			display = displaySearchList(criteria);
		}
		request.setAttribute("display", display);
		getServletContext().getRequestDispatcher("/AdminSearch.jsp").forward(
				request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String criteria = request.getParameter("select");
		String display = "";
		System.out.println(criteria);
		
		if (criteria != null) {
			display = displaySearchList(criteria);
		}
		request.setAttribute("display", display);

		getServletContext().getRequestDispatcher("/AdminSearch.jsp").forward(
				request, response);
	}

	protected static String displaySearchList(String criteria) {
		StringBuilder display = new StringBuilder();
		switch (criteria) {
		case ("student"):

			display.append(displayStudents(Admin.getAllStudents()));
			break;
		case ("instructor"):
			display.append(displayInstructors(Admin.getAllInstructors()));
			break;
		case ("advisor"):
			display.append(displayAdvisors(Admin.getAllAdvisors()));
			break;
		case ("department"):
			display.append("<div class = \"container\"> <a href=\"AdminCreate?select=department\" class=\"btn btn-info\" role=\"button\">Add</a></div>");
			//display.append(departmentCreationForm());
			display.append(displayDepartments(Admin.getAllDepartments()));
			break;
		case ("course"):
			display.append("<div class = \"container\"> <a href=\"AdminCreate?select=course\" class=\"btn btn-info\" role=\"button\">Add</a></div>");
			//display.append(courseCreationForm());
			display.append(displayCourses(Admin.getAllCourses()));
			break;
		case ("major"):
			display.append("<div class = \"container\"> <a href=\"AdminCreate?select=major\" class=\"btn btn-info\" role=\"button\">Add</a></div>");
		//	display.append(majorCreationForm());
			display.append(displayMajors(Admin.getAllMajors()));
			break;
		case ("class"):
			display.append("<div class = \"container\"> <a href=\"AdminCreate?select=class\" class=\"btn btn-info\" role=\"button\">Add</a></div>");
		//	display.append(classCreationForm());
			display.append(displayClasses(Admin.getAllClasses()));
			break;
		case ("classroom"):
			display.append("<div class = \"container\"> <a href=\"AdminCreate?select=classroom\" class=\"btn btn-info\" role=\"button\">Add</a></div>");
		//	display.append(classroomCreationForm());
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
				display.append("<tr class='clickable-row' data-href= \"#\">");

			} else {
				display.append("<tr class='clickable-row' data-href= \"#\" style=\"color: #fff; background: black;\">");
			}
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
					+ "<a href=\"AdminUpdate?id=" + d.getDepartmentId()
					+ "&type=department"
					+ "\" class=\"btn btn-info\" role=\"button\">Update</a>"
					+ "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected static String displayCourses(List<Hcours> courses) {
		StringBuilder display = new StringBuilder();

		display.append("<div class=\"container\"><h2>Courses</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Subject</th><th>Credit Hours</th></tr></thead><tbody>");
		for (Hcours c : courses) {
			if (c.getEnabled().equals("yes")) {
				display.append("<tr class='clickable-row' data-href= \"#\"><td>");
			} else {
				display.append("<tr class='clickable-row' data-href= \"#\" style=\"color: #fff; background: black;\"><td>");
			}
			display.append(c.getSubject()
					+ "</td><td>"
					+ c.getCreditHours()
					+ "</td><td>"
					+ "<a href=\"Enable?todo=enable&type=course&id="
					+ c.getCourseId()
					+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
					+ "<a href=\"Enable?todo=disable&type=course&id="
					+ c.getCourseId()
					+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
					+ "<a href=\"AdminUpdate?id=" + c.getCourseId()
					+ "&type=course"
					+ "\" class=\"btn btn-info\" role=\"button\">Update</a>"
					+ "</td></tr>");
		}

		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected static String displayMajors(List<Hmajor> majors) {
		StringBuilder display = new StringBuilder();

		display.append("<div class=\"container\"><h2>Majors</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Name</th><th>Department</th></tr></thead><tbody>");
		for (Hmajor m : majors) {
			if (m.getEnabled().equals("yes")) {
				display.append("<tr nclass='clickable-row' data-href= \"#\"><td>");

			} else {
				display.append("<tr class='clickable-row' data-href= \"#\" style=\"color: #fff; background: black;\"><td>");
			}
			display.append(m.getName()
					+ "</td><td>"
					+ m.getHdepartment().getName()
					+ "</td><td>"
					+ "<a href=\"Enable?todo=enable&type=major&id="
					+ m.getMajorId()
					+ "\" class=\"btn btn-success\" role=\"button\">Enable</button>"
					+ "<a href=\"Enable?todo=disable&type=major&id="
					+ m.getMajorId()
					+ "\" class=\"btn btn-danger\" role=\"button\">Disable</button>"
					+ "<a href=\"AdminUpdate?id=" + m.getMajorId()
					+ "&type=major"
					+ "\" class=\"btn btn-info\" role=\"button\">Update</a>"
					+ "</td></tr>");
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
			if (c.getEnabled().equals("yes")) {
				display.append("<tr class='clickable-row' data-href= \"#\"><td>");

			} else {
				display.append("<tr class='clickable-row' data-href= \"#\" style=\"color: #fff; background: black;\"><td>");
			}
			display.append(c.getHcours().getCourseId()
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
					+ "<a href=\"AdminUpdate?id=" + c.getClassId()
					+ "&type=class"
					+ "\" class=\"btn btn-info\" role=\"button\">Update</a>"
					+ "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected static String displayClassrooms(List<Hclassroom> classrooms) {
		StringBuilder display = new StringBuilder();

		display.append("<div class=\"container\"><h2>Classrooms</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Room Number</th><th>Building</th><th>Capacity</th></tr></thead><tbody>");
		for (Hclassroom c : classrooms) {
			if (c.getEnabled().equals("yes")) {
				display.append("<tr class='clickable-row' data-href= \"#\"><td>");

			} else {
				display.append("<tr class='clickable-row' data-href= \"#\" style=\"color: #fff; background: black;\"><td>");
			}
			display.append(c.getRoomNumber()
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
					+ "<a href=\"AdminUpdate?id=" + c.getClassroomId()
					+ "&type=classroom"
					+ "\" class=\"btn btn-info\" role=\"button\">Update</a>"
					+ "</td></tr>");
		}

		display.append("</tbody></table></div>");
		return display.toString();
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


	

}
