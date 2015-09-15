package himanshu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Huser;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		//authenticate
		Huser success = Authentication.authenticate_user(userid, password);
		if(success!=null)	//if there is a Huser object
		{

			int month = Calendar.getInstance().get(Calendar.MONTH); 
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			String sem=null;
			if(month >= 0 && month <= 4 )
				sem = "spring";
			else
				sem= "fall";
			request.getSession().setAttribute("currentYear", currentYear);	
			request.getSession().setAttribute("currentSemester", sem);	
			request.getSession().setAttribute("User", success);	
			String message = "Succesfully Signed In!";
			request.setAttribute("message", message);
			request.getServletContext().getRequestDispatcher("/Success.jsp")
			.forward(request, response);
		}
		else	//Redirect to Login Page
		{
			request.getServletContext().getRequestDispatcher("/SignIn.jsp")
			.forward(request, response);
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}
