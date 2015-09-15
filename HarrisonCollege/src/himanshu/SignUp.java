package himanshu;

import java.io.IOException;
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

		Random r = new Random();
		int userid = 1 + r.nextInt(99999);

		// need to check if already exists

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
			String departments[] = new String[100];
			String majors[] = new String[100];
			int index = 0;
			for (Hmajor temp : mlist) {
				majors[index] = temp.getName();
				System.out.println(temp.getName());
				index++;
			}

			request.setAttribute("darray", departments);
			request.setAttribute("marray", majors);

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
			String departments[] = new String[100];
			int index = 0;
			for (Hdepartment temp : dlist) {
				departments[index] = temp.getName();
				System.out.println(temp.getName());
				index++;
			}
			index = 0;
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
