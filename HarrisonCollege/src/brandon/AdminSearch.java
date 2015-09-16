package brandon;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println(criteria);
		String display = getSearchList(criteria);
		request.setAttribute("display", display);
		doGet(request, response);
	}

	protected String getSearchList(String criteria) {

		switch (criteria) {
		case ("students"):
			return displayStudents(Admin.getAllStudents());
		case ("instructors"):
			return displayInstructors(Admin.getAllInstructors());
		case ("advisors"):
			return displayAdvisors(Admin.getAllAdvisors());
		case ("departments"):
			return displayDepartments(Admin.getAllDepartments());
		case ("courses"):
			return displayCourses(Admin.getAllCourses());
		case ("majors"):
			return displayMajors(Admin.getAllMajors());
		case ("classes"):
			return displayClasses(Admin.getAllClasses());
		case ("classrooms"):
			return displayClassrooms(Admin.getAllClassrooms());
		default:
			return null;
		}
	}

	protected String displayStudents(List<Hstudent> students) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Students</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Name</th><th>Entry Year</th></tr></thead><tbody>");
		for (Hstudent s : students) {
			display.append("<tr><td>" + s.getStudentId() + "</td><td>"
					+ s.getHuser().getName() + "</td><td>" + s.getEntryYear()
					+ "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected String displayInstructors(List<Hofficial> instructors) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Instructors</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Name</th><th>Department</th> <th>Office #</th></tr></thead><tbody>");
	
		for (Hofficial i : instructors) {
			display.append("<tr><td>" + i.getOfficialId() + "</td><td>" + i.getHuser().getName()
					+ "</td><td>" + i.getHdepartment().getName() + "</td><td>"
					+ i.getOfficeNumber() + "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected String displayAdvisors(List<Hofficial> advisors) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Advisors</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Name</th><th>Department</th> <th>Office #</th></tr></thead><tbody>");
	
		for (Hofficial a : advisors) {
			display.append("<tr><td>" + a.getOfficialId() + "</td><td>" + a.getHuser().getName()
					+ "</td><td>" + a.getHdepartment().getName() + "</td><td>"
					+ a.getOfficeNumber() + "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected String displayDepartments(List<Hdepartment> departments) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Departments</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Code</th><th>Name</th></tr></thead><tbody>");
		for (Hdepartment d : departments) {
			display.append("<tr><td>" +d.getCode() + "</td><td>" + d.getName() + "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected String displayCourses(List<Hcours> courses) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Courses</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Subject</th><th>Credit Hours</th></tr></thead><tbody>");
		for (Hcours c : courses) {
			display.append("<tr><td>" + c.getCourseId() + "</td><td>" + c.getSubject() + "</td><td>"
					+ c.getCreditHours() + "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected String displayMajors(List<Hmajor> majors) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Majors</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Name</th><th>Department</th></tr></thead><tbody>");
		for (Hmajor m : majors) {
			display.append("<tr><td>" +m.getName() + "</td><td>" + m.getHdepartment().getName()+ "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected String displayClasses(List<Hclass> classes) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Classes</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Subject</th><th>Day</th>"
				+ "<th>Start Time</th><th>End Time</th><th>Semester</th><th>Year</th></tr></thead><tbody>");
		for (Hclass c : classes) {
			display.append("<tr><td>" +c.getHcours().getCourseId() + "</td><td>"
					+ c.getHcours().getSubject() + "</td><td>" + c.getDay() + "</td><td>"
					+ c.getStarttime() + "</td><td>" + c.getEndtime() +"</td><td>"
					+ c.getSemester() + "</td><td>" + c.getYear()+ "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

	protected String displayClassrooms(List<Hclassroom> classrooms) {
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\"><h2>Classrooms</h2>"
				+ "<table class=\"table table-hover\"><thead><tr><th>Room Number</th><th>Building</th><th>Capacity</th></tr></thead><tbody>");
		for (Hclassroom c : classrooms) {
			display.append("<tr><td>" +c.getRoomNumber() + "</td><td>" + c.getBuilding() + "</td><td>"
					+ c.getCapacity()+ "</td></tr>");
		}
		display.append("</tbody></table></div>");
		return display.toString();
	}

}
