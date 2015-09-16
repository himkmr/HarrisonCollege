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
			request.getSession().setAttribute("usertype", success.getPermissions());	
			request.getSession().setAttribute("currentYear", currentYear+"");	
			request.getSession().setAttribute("currentSemester", sem);	
			request.getSession().setAttribute("User", success);	
			
			if(success.getPermissions().equalsIgnoreCase("Admin"))
			{	
			request.getServletContext().getRequestDispatcher("/AdminPendingRequest").forward(request, response);}
			else if(success.getPermissions().equalsIgnoreCase("Student"))
			{
				request.getServletContext().getRequestDispatcher("/Success.jsp").forward(request, response);
			}
			else if(success.getPermissions().equalsIgnoreCase("Instructor"))
			{
				System.out.println("Redirecting Instructor");
				request.getServletContext().getRequestDispatcher("/InstructorFirstPage").forward(request, response);
			}
			else if(success.getPermissions().equalsIgnoreCase("Advisor"))
			{
				request.getServletContext().getRequestDispatcher("/Success.jsp").forward(request, response);
			}
			else if(success.getPermissions().equalsIgnoreCase("Intructor_Advisor"))
			{
				request.getServletContext().getRequestDispatcher("/Success.jsp").forward(request, response);
			}
			else
			{
				request.getServletContext().getRequestDispatcher("/Success.jsp").forward(request, response);
				
			}
			
		
			
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
