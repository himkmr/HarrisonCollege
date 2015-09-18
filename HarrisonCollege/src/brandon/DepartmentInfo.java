package brandon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.*;

/**
 * Servlet implementation class DepartmentInfo
 */
@WebServlet("/DepartmentInfo")
public class DepartmentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		Hdepartment department = DBUtil.find(id, Hdepartment.class);
		HttpSession session = request.getSession();
		String currentSemester = (String) session.getAttribute("currentSemester");
		String deptInfo = displayDepartmentInfo(department);
		String courses = Display.displayCourses(department.getHcourses());
		String currentClasses = Display.displayClasses(Admin.currentClassesByDepartment(department, currentSemester));
		String majors = Display.displayMajors(department.getHmajors());
		request.setAttribute("courses",courses);
		request.setAttribute("deptInfo",deptInfo);
		request.setAttribute("currentClasses",currentClasses);
		request.setAttribute("majors",majors);
		getServletContext().getRequestDispatcher("/DepartmentInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected static String displayDepartmentInfo(Hdepartment department){
		List<Hdepartment> departments = new ArrayList<Hdepartment>();
		departments.add(department);
		return Display.displayDepartments(departments);
	}
	
	

}
