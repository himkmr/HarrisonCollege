package Meenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class Classenrollment
 */
@WebServlet("/Classenrollment")
public class Classenrollment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Classenrollment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Huser thisUser = (Huser) session.getAttribute("User");
		long studentID = thisUser.getHstudent().getStudentId();
		if(thisUser.getPermissions().equalsIgnoreCase("advisor"))
			studentID = Long.parseLong(request.getParameter("studentID"));
		String classID = request.getParameter("classID");
		String starttime = request.getParameter("stime");
		String endtime = request.getParameter("etime");
		int stime = Integer.parseInt(starttime);
		int etime = Integer.parseInt(endtime);
		int capacity = Integer.parseInt(request.getParameter("rcap"));
		Hclassenrollment student = new Hclassenrollment();
		if(Student.checkschedule(studentID, classID, capacity, stime, etime)==true){
			System.out.println("adding student");
			student.setEnrolled("yes");
			student.setGrade("W");
			student.setHclass(Student.getClass(classID));
			student.setHstudent(Student.getStudent(studentID));
			Student.addClass(student);
			System.out.println("Added student");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
