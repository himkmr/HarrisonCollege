package himanshu;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hpendingadmission;
import model.Hstudent;
import model.Huser;
import customTools.DBUtil;

/**
 * Servlet implementation class SignUpOffiicial
 */
@WebServlet("/SignUpOfficial")
public class SignUpOfficial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpOfficial() {
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
				
				//get department type from parameters
				String department  = request.getParameter("departments");
				System.out.println(userid);
				String permissions = user.getPermissions();
				String password = user.getPassword();
				String name = user.getName();
				//Create a pendingAdmission object and set data
				Hpendingadmission padmission = new Hpendingadmission();
				padmission.setUserid(userid);
				padmission.setPermissions(permissions);
				String msg= name+","+password+","+department;
				padmission.setMessage(msg);
				
				DBUtil.addToDB(padmission);
				request.setAttribute("userid", userid);
				request.setAttribute("message", " Request Successfully Submitted! Please note you UserID");
				
				request.getServletContext().getRequestDispatcher("/Success.jsp")
				.forward(request, response);

	}

}
