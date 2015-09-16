package brandon;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class AdminPendingRequest
 */
@WebServlet("/AdminPendingRequest")
public class AdminPendingRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPendingRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * list pending admissions and allow for denial or acceptance
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pending", displayPending());
		getServletContext().getRequestDispatcher("/AdminPendingRequest.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected String displayPending(){
		List<Hpendingadmission> admissions = Admin.getPendingAdmissions();
		StringBuilder pending = new StringBuilder();
		for(Hpendingadmission a : admissions){
			System.out.println(a.getMessage());
			pending.append("<li class=\"list-group-item\">"+ a.getMessage() + "<a href=\"ApproveAdmissions?admisId=" + a.getPendingId() +"&approval=accept\" class=\"btn btn-info\" role=\"button\">Accept</a><a href=\"ApproveAdmissions?admisId=" + a.getPendingId() +"&approval=decline\" class=\"btn btn-info\" role=\"button\">Decline</a></li>");
		}
		return pending.toString();
	}

}
