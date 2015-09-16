package brandon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import customTools.DBUtil;

/**
 * Servlet implementation class StudentInfo
 */
@WebServlet("/StudentInfo")
public class StudentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Hstudent student = DBUtil.find(Long.parseLong(request.getParameter("id")), Hstudent.class);
		String info = displayStudentInfo(student);
		String classes = displayStudentClasses(student);
		request.setAttribute("info",info);
		request.setAttribute("classes",classes);
		getServletContext().getRequestDispatcher("/AdminInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected String displayStudentInfo(Hstudent student){
		StringBuilder display = new StringBuilder();
		display.append("<div class=\"container\">"+student.getStudentId() + " " + student.getHuser().getName() + " " + student.getEntryYear() + " " + student.getMajor().getName() + "</div>");
		return display.toString();
	}
	
	protected String displayStudentClasses(Hstudent student){
		List<Hclass> classes = new ArrayList<Hclass>();
		for(Hclassenrollment e : student.getHclassenrollments()){
			classes.add(e.getHclass());
		}
		return AdminSearch.displayClasses(classes);
	}


}
