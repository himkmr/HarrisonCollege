

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brandon.Admin;
import customTools.DBUtil;
import model.Hmajor;
import model.Hpendingadmission;
import model.Hstudent;
import model.Huser;

/**
 * Servlet implementation class SignUpStudent
 */
@WebServlet("/SignUpStudent")
public class SignUpStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//get the HUser from the Session
		Huser user = (Huser) request.getSession().getAttribute("User");
		long userid = user.getUserId();
		String major  = request.getParameter("majors");
		System.out.println(userid);
		String entryYear = request.getParameter("year");
		String permissions = user.getPermissions();
		String password = user.getPassword();
		String name = user.getName();
		
		Hmajor major_obj = Admin.getMajor(major);
		//create a HStudent
		Hstudent student = new Hstudent();
		student.setEntryYear(entryYear);
		student.setMajor(major_obj);
		student.setStudentId(userid);
	
		
//		//trying adding to DB, works!
//		DBUtil.addToDB(user);
//		DBUtil.addToDB(student);

		Hpendingadmission padmission = new Hpendingadmission();
		padmission.setUserid(userid);
		padmission.setPermissions(permissions);
		String msg= name+","+password+","+major+","+entryYear;
		padmission.setMessage(msg);
		
		DBUtil.addToDB(padmission);
		request.setAttribute("userid", userid);
		request.setAttribute("message", " Request Successfully Submitted! Please note you UserID");
		
		request.getServletContext().getRequestDispatcher("/Success.jsp")
		.forward(request, response);

		
	}

}
