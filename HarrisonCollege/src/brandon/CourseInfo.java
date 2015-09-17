package brandon;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import customTools.DBUtil;

/**
 * Servlet implementation class CourseInfo
 */
@WebServlet("/CourseInfo")
public class CourseInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Hcours course = DBUtil.find(Long.parseLong(request.getParameter("id")),Hcours.class);
		String info = displayCourseInfo(course);
		String classrooms = Display.displayClassrooms(Admin.classroomsByCourse(course));
		String classes = Display.displayClasses(Admin.classesByCourse(course));
		request.setAttribute("classrooms", classrooms);
		request.setAttribute("classes", classes);
		request.setAttribute("info", info);
		getServletContext().getRequestDispatcher("/CourseInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected static String displayCourseInfo(Hcours course){
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\">"+course.getCourseId()+ " " + course.getSubject() + " "+ course.getHdepartment().getName()+ " " + course.getCreditHours()+ "</div>");
		return display.toString();
	}
	
	
	
	
	

}
