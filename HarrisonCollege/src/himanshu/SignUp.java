package himanshu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.TypedQuery;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;
import model.Hdepartment;
import model.Hmajor;
import model.Hpendingadmission;
import model.Huser;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// find the user type

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String permissions = request.getParameter("permissions");
		System.out.println(name);
		System.out.println(password);

		
		// need to check if already exists
		int userid = GetUniqueUserId();
		

		Huser user = new Huser();
		user.setName(name);
		user.setPassword(password);
		user.setUserId(userid);
		user.setPermissions(permissions);
		request.getSession().setAttribute("User", user);

		// Redirect to their specific SignUp page
		if (permissions.equalsIgnoreCase("Student")) {
			getStudentData(request, response);
		} else if (permissions.equalsIgnoreCase("Admin")) {
			// To do Later
		} else // Official : Instructor or Advisor
		{
			getOfficialData(request, response);
		}
	}

	private int GetUniqueUserId() {
		Random r = new Random();
		int userid = 1 + r.nextInt(99999);
		//check in Pending Admissions
		String q = "select t from Hpendingadmission t  where t.userid="+userid;
		TypedQuery<Hpendingadmission>  exists1 = DBUtil.createQuery(q, Hpendingadmission.class);
		List<Hpendingadmission> user1 =exists1.getResultList();
		
		//check in Users
		String q2 = "select t from Huser t  where t.userId="+userid;
		TypedQuery<Huser>  exists2 = DBUtil.createQuery(q2, Huser.class);
		List<Huser> user2 =exists2.getResultList();
		
		if((user2==null || user2.isEmpty()) && (user1==null || user2.isEmpty()) )
			return userid;
		else
			userid = GetUniqueUserId();
		
		return userid;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	
	
	
	
	
	public static void getStudentData(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String q1 = "select t from Hmajor t";
			TypedQuery<Hmajor> tq2 = DBUtil.createQuery(q1, model.Hmajor.class);
			List<Hmajor> mlist = tq2.getResultList();
			List <String> marray = new ArrayList<String>();

			for (Hmajor temp : mlist) {
				marray.add(temp.getName());
			}

			request.setAttribute("marray", marray);

			request.getServletContext()
					.getRequestDispatcher("/StudentSignUp.jsp")
					.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	
	public static void getOfficialData(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String q = "select t from Hdepartment t";
			TypedQuery<Hdepartment> tq = DBUtil.createQuery(q,
					model.Hdepartment.class);
			List<Hdepartment> dlist = tq.getResultList();
			List <String> departments =new ArrayList<String>();

			for (Hdepartment temp : dlist) {
				departments.add(temp.getName());
			}
			request.setAttribute("darray", departments);
			request.getServletContext()
					.getRequestDispatcher("/OfficialSignUp.jsp")
					.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
