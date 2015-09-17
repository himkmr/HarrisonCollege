package brandon;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import customTools.DBUtil;

/**
 * Servlet implementation class AdminUpdate
 */
@WebServlet("/AdminUpdate")
public class AdminUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		long id = Long.parseLong(request.getParameter("id"));
		String displayForm = updateInfoForm(type,id);
		request.setAttribute("displayForm",displayForm);
		getServletContext().getRequestDispatcher("/AdminUpdateCreate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("selectType");
		long id = Long.parseLong(request.getParameter("selectId"));
		updateInfo(type,id,request);
		response.sendRedirect("AdminSearch?select="+type);
		}
	
	protected static String updateInfoForm(String type, long id){
		switch(type){
		case("department"): return updateDepartmentForm(id);
		case("course"):	return updateCourseForm(id);
		case("class"):	return updateClassForm(id);
		case("classroom"): return updateClassroomForm(id);
		case("major"): return updateMajorForm(id);
		default: return "";
		
		}
	}
	
	protected static String updateDepartmentForm(long id){
		Hdepartment d = DBUtil.find(id, Hdepartment.class);
		return "<div class=\"container\"><form class=\"form-vertical\" role=\"form\" method=\"post\" action=\"AdminUpdate\">"
				+ "<div class=\"form-group\"><label for=\"code\">Code:</label><input type=\"text\" class=\"form-control\""
				+ " id=\"code\" name =\"code\" placeholder=\""+d.getCode()+"\"></div><div class=\"form-group\"><label for=\"name\">Name:</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"name\" name =\"name\" placeholder=\""+d.getName()+"\"></div>"
				+ "<input type=\"hidden\" name=\"selectId\" value=\""+d.getDepartmentId()+"\"/>"
				+"<input type=\"hidden\" name=\"selectType\" value=\"department\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Update</button></form></div>";
	}
	
	protected static String updateCourseForm(long id){
		Hcours c = DBUtil.find(id, Hcours.class);
		return "<div class=\"container\"><form class=\"form-vertical\" role=\"form\" method=\"post\" action=\"AdminUpdate\">"
				+ "<div class=\"form-group\"><label for=\"subject\">Subject</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"subject\" name =\"subject\" placeholder=\""+c.getSubject()+"\"></div>"
				+ "<div class=\"form-group\"><label for=\"hours\">Credit Hours:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"hours\" name =\"hours\" min = \"0\" placeholder=\""+c.getCreditHours()+"\"></div>"
				+ "<div class=\"form-group\"><label for=\"selDepartment\">Select Department:</label>"
				+ "<select class=\"form-control\" id=\"selDepartment\" name =\"selDepartment\" placeholder =" + c.getHdepartment().getName()+">"
				+ AdminSearch.listDepartments()
				+ "</select></div>"
				+ "<input type=\"hidden\" name=\"select\" value=\"course\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Update</button></form></div>";
	}

	protected static String updateClassForm(long id){
		Hclass c = DBUtil.find(id,Hclass.class);
		return "<div class=\"container\"><form class=\"form-vertical\" role=\"form\" method=\"post\" action=\"AdminSearch\">"
				+ "<div class=\"form-group\"><label for=\"selCours\">Select Course:</label>"
				+ "<select class=\"form-control\" id=\"selCourse\" name =\"selCourse\" placeholder = \"" + c.getHcours().getSubject()+"\">"
				+ AdminSearch.listCourses()
				+ "</select></div>"

				+ "<div class=\"form-group\"><label for=\"selClassroom\">Select Classroom:</label>"
				+ "<select class=\"form-control\" id=\"selClassroom\" name =\"selClassroom\" placeholder =\"" + c.getHclassroom().getRoomNumber() + " " + c.getHclassroom().getBuilding() + "\">"
				+ AdminSearch.listClassrooms()
				+ "</select></div>"

				+ "<div class =\"form-group\"><label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"monday\" value=\"M\">M</label>"
				+ "<label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"tuesday\" value=\"T\">T</label><label class=\"checkbox-inline\">"
				+ "<input type=\"checkbox\" name=\"wednesday\" value=\"W\">W</label><label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"thursday\" value=\"H\">H"
				+ "</label><label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"friday\" value=\"F\">F</label></div>"

				+ "<div class=\"form-group\"><label for=\"startTime\">Start Time:</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"startTime\" name =\"startTime\" placeholder=\""+c.getStarttime()+"\"></div>"

				+ "<div class=\"form-group\"><label for=\"endTime\">End Time:</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"endTime\" name =\"endTime\" placeholder=\""+c.getEndtime()+"\"></div>"

				+ "<div class=\"form-group\"><label for=\"selSemester\">Select Semester:</label>"
				+ "<select class=\"form-control\" id=\"selSemester\" name =\"selSemester\">"
				+ "<option value =\"Fall\">Fall</option><option value =\"Spring\">Spring</option>"
				+ "</select></div>"

				+ "<div class=\"form-group\"><label for=\"year\">Year:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"year\" name =\"year\" min =\"2015\" max = \"2070\" placeholder=\""+c.getYear()+"\"></div>"

				+ "<input type=\"hidden\" name=\"select\" value=\"class\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Update</button></form></div>";
	}

	protected static String updateClassroomForm(long id){
		Hclassroom c = DBUtil.find(id, Hclassroom.class);
		return "<div class=\"container\"><form class=\"form-vertical\" role=\"form\" method=\"post\" action=\"AdminUpdate\">"

				+ "<div class=\"form-group\"><label for=\"building\">Building:</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"building\" name =\"building\" placeholder=\""+c.getBuilding()+"\"></div>"

				+ "<div class=\"form-group\"><label for=\"capacity\">Capacity:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"capacity\" name =\"capacity\" min =\"0\" placeholder=\""+c.getCapacity()+"\"></div>"

				+ "<div class=\"form-group\"><label for=\"roomNumber\">Room Number:</label>"
				+ "<input type=\"number\" class=\"form-control\" id=\"roomNumber\" min =\"100\" value=\"100\" name =\"roomNumber\" placeholder=\""+c.getRoomNumber()+"\"></div>"

				+ "<input type=\"hidden\" name=\"select\" value=\"classroom\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Update</button></form></div>";
	}
	
	protected static String updateMajorForm(long id){
		Hmajor m = DBUtil.find(id, Hmajor.class);
		return "<div class=\"container\"><form class=\"form-vertical\" role=\"form\" method=\"post\" action=\"AdminUpdate\">"
				+ "<div class=\"form-group\"><label for=\"name\">Name</label>"
				+ "<input type=\"text\" class=\"form-control\" id=\"name\" name =\"name\" placeholder=\""+m.getName()+"\"></div>"
				+ "<div class=\"form-group\"><label for=\"selDepartment\">Select Department:</label>"
				+ "<select class=\"form-control\" id=\"selDepartment\" name =\"selDepartment\" placeholder=\""+m.getHdepartment().getName()+"\">"
				+ AdminSearch.listDepartments()
				+ "</select></div>"
				+ "<input type=\"hidden\" name=\"select\" value=\"major\"/>"
				+ "<button type=\"submit\" class=\"btn btn-default\">Update</button></form></div>";
	}
	
	protected static void updateInfo(String type, long id, HttpServletRequest request){
		switch(type){
		case("department"): updateDepartment(request, id); break;
		case("course"):	updateCourse(request,id); break;
		case("class"):	updateClass(request,id); break;
		case("classrooom"):updateClassroom(request,id);break;
		case("major"):updateMajor(request,id);break;
		}
	}
	
	private static void updateClassroom(HttpServletRequest request, long id) {
		Hclassroom classroom = DBUtil.find(id, Hclassroom.class);
		String building = request.getParameter("building");
		String capacity = request.getParameter("capacity");
		String roomNumber = request.getParameter("roomNumber");
		if(!building.equals("")){
			Admin.updateClassroom(classroom, "building", building);
		}
		if(!capacity.equals("")){
			Admin.updateClassroom(classroom, "capacity", Integer.parseInt(capacity));
		}
		if(!roomNumber.equals("")){
			Admin.updateClassroom(classroom, "roomNumber", Integer.parseInt(roomNumber));
		}
		
	}

	private static void updateClass(HttpServletRequest request, long id) {
		Hclass hclass = DBUtil.find(id, Hclass.class);
		String selCourse = request.getParameter("selCourse");
		String selClassroom = request.getParameter("selClassroom");
		String days = AdminCreate.printDays(request);
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String semester = request.getParameter("selSemester");
		String year = request.getParameter("year");
		if(!selCourse.equals("")){
			Hcours course = DBUtil.find(Long.parseLong(selCourse),Hcours.class);
			Admin.updateClass(hclass, "course", course);
		}
		if(!selClassroom.equals("")){
			Hclassroom classroom = DBUtil.find(Long.parseLong(selClassroom),Hclassroom.class);
			Admin.updateClass(hclass, "classroom", classroom);
		}
		if(!days.equals("")){
			Admin.updateClass(hclass, "days", days);
		}
		if(!startTime.equals("")){
			Admin.updateClass(hclass, "starttime", startTime);
		}
		if(!endTime.equals("")){
			Admin.updateClass(hclass, "endtime",endTime);
		}
		if(!semester.equals("")){
			Admin.updateClass(hclass, "semester", semester);
		}
		if(!year.equals("")){
			Admin.updateClass(hclass, "year", year);
		}
		
	}

	private static void updateCourse(HttpServletRequest request, long id) {
		Hcours course = DBUtil.find(id,Hcours.class);
		String subject = request.getParameter("subject");
		String hours = request.getParameter("hours");
		String selDepartment = request.getParameter("selDepartment");
		if(!subject.equals("")){
			Admin.updateCourse(course, subject);
		}
		if(!hours.equals("")){
			Admin.updateCourse(course, Integer.parseInt(hours));
		}	
		if(!selDepartment.equals("")){
			Hdepartment department = DBUtil.find(Integer.parseInt(selDepartment), Hdepartment.class);
			Admin.updateCourse(course, department);
		}		
	}

	protected static void updateDepartment(HttpServletRequest request, long id){
		Hdepartment department = DBUtil.find(id,Hdepartment.class);
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		System.out.println("CODE " + code + "NAME " + name);
		if(!code.equals("")){
			Admin.updateDepartment(department, "code", code);
		}
		if(!name.equals("")){
			Admin.updateDepartment(department, "name", name);
		}	
	}
	
	protected static void updateMajor(HttpServletRequest request, long id){
		Hmajor major = DBUtil.find(id, Hmajor.class);
		String name = request.getParameter("name");
		String deptId = request.getParameter("selDepartment");
		if(!name.equals("")){
			Admin.updateMajor(major, "name", name);
		}
		if(!deptId.equals("")){
			Hdepartment department = DBUtil.find(Long.parseLong("deptId"),Hdepartment.class);
			Admin.updateMajor(major, "department", department);
		}
	}
	
	


}
