package brandon;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hclassroom;
import model.Hcours;
import model.Hdepartment;
import customTools.DBUtil;

/**
 * Servlet implementation class AdminCreate
 */
@WebServlet("/AdminCreate")
public class AdminCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("select");
		String displayForm = createInfoForm(type);
		request.setAttribute("displayForm",displayForm);
		getServletContext().getRequestDispatcher("/AdminUpdateCreate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type="";
		if (departmentFormAvailable(request)) {
			Admin.createDepartment(request.getParameter("code"),
					request.getParameter("name"));
			type="department";
		} else if (courseFormAvailable(request)) {
			Hdepartment department = DBUtil.find(
					Long.parseLong(request.getParameter("selDepartment")),
					Hdepartment.class);
			System.out.println(department.getName());
			Admin.createCourse(department, request.getParameter("subject"),
					Integer.parseInt(request.getParameter("hours")));
			type="course";
		} else if (majorFormAvailable(request)) {
			Hdepartment department = DBUtil.find(
					Long.parseLong(request.getParameter("selDepartment")),
					Hdepartment.class);
			System.out.println(department.getName());
			Admin.createMajor(department, request.getParameter("name"));
			type="major";
		} else if (classFormAvailable(request)) {
			Hclassroom classroom = DBUtil.find(
					Long.parseLong(request.getParameter("selClassroom")),
					Hclassroom.class);
			Hcours course = DBUtil.find(
					Long.parseLong(request.getParameter("selCourse")),
					Hcours.class);
			Admin.createClass(course, printDays(request), classroom,
					request.getParameter("startTime"),
					request.getParameter("endTime"),
					request.getParameter("selSemester"),
					request.getParameter("year"));
			type="class";
		} else if (classroomFormAvailable(request)) {
			Admin.createClassroom(request.getParameter("building"),
					Integer.parseInt(request.getParameter("capacity")),
					Integer.parseInt(request.getParameter("roomNumber")));
			type="classroom";
		}
		
		response.sendRedirect("AdminSearch?select="+type);
	}
	
	protected static String createInfoForm(String type){
		switch(type){
		case("department"): return createDepartmentForm();
		case("course"):	return createCourseForm();
		case("class"):	return createClassForm();
		case("classroom"): return createClassroomForm();
		case("major"): return createMajorForm();
		default: return "";
		
		}
	}
	

	protected static String createDepartmentForm() {
		return "<div class=\"container\"><form class=\"form-vertical\" role=\"form\" method=\"post\" action=\"AdminCreate\">"
				+ "<div class=\"form-group\"><label for=\"code\">Code:</label><input type=\"text\" class=\"form-control\""
				+ " id=\"code\" name =\"code\" placeholder=\"Enter Code\"></div><div class=\"form-group\"><label for=\"name\">Name:</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"name\" name =\"name\" placeholder=\"Enter Name\"></div>"
				+ "<input type=\"hidden\" name=\"select\" value=\"departments\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Add</button></form></div>";

	}

	protected static String createCourseForm() {
		return "<div class=\"container\"><form class=\"form-vertical\" role=\"form\" method=\"post\" action=\"AdminCreate\">"
				+ "<div class=\"form-group\"><label for=\"subject\">Subject</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"subject\" name =\"subject\" placeholder=\"Enter Subject\"></div>"
				+ "<div class=\"form-group\"><label for=\"hours\">Credit Hours:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"hours\" name =\"hours\" min =\"0\" placeholder=\"Enter Credit Hours\"></div>"
				+ "<div class=\"form-group\"><label for=\"selDepartment\">Select Department:</label>"
				+ "<select class=\"form-control\" id=\"selDepartment\" name =\"selDepartment\">"
				+ AdminSearch.listDepartments()
				+ "</select></div>"
				+ "<input type=\"hidden\" name=\"select\" value=\"courses\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Add</button></form></div>";

	}

	protected static String createMajorForm() {
		return "<div class=\"container\"><form class=\"form-vertical\" role=\"form\" method=\"post\" action=\"AdminCreate\">"
				+ "<div class=\"form-group\"><label for=\"name\">Name</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"name\" name =\"name\" placeholder=\"Enter Name\"></div>"
				+ "<div class=\"form-group\"><label for=\"selDepartment\">Select Department:</label>"
				+ "<select class=\"form-control\" id=\"selDepartment\" name =\"selDepartment\">"
				+ AdminSearch.listDepartments()
				+ "</select></div>"
				+ "<input type=\"hidden\" name=\"select\" value=\"majors\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Add</button></form></div>";

	}

	protected static String createClassForm() {
		return "<div class=\"container\"><form class=\"form-vertical\" role=\"form\" method=\"post\" action=\"AdminCreate\">"
				+ "<div class=\"form-group\"><label for=\"selCours\">Select Course:</label>"
				+ "<select class=\"form-control\" id=\"selCourse\" name =\"selCourse\">"
				+ AdminSearch.listCourses()
				+ "</select></div>"

				+ "<div class=\"form-group\"><label for=\"selClassroom\">Select Classroom:</label>"
				+ "<select class=\"form-control\" id=\"selClassroom\" name =\"selClassroom\">"
				+ AdminSearch.listClassrooms()
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

	protected static String createClassroomForm() {
		return "<div class=\"container\"><form class=\"form-vertical\" role=\"form\" method=\"post\" action=\"AdminCreate\">"

				+ "<div class=\"form-group\"><label for=\"building\">Building:</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"building\" name =\"building\" placeholder=\"Enter Building\"></div>"

				+ "<div class=\"form-group\"><label for=\"capacity\">Capacity:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"capacity\" name =\"capacity\" min =\"0\" value=\"0\"></div>"

				+ "<div class=\"form-group\"><label for=\"roomNumber\">Room Number:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"roomNumber\" min =\"100\" value=\"100\" name =\"roomNumber\"></div>"

				+ "<input type=\"hidden\" name=\"select\" value=\"classrooms\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Add</button></form></div>";

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
