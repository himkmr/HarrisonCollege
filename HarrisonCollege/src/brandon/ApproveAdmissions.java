package brandon;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hpendingadmission;
import customTools.DBUtil;

/**
 * Servlet implementation class ApproveAdmissions
 */
@WebServlet("/ApproveAdmissions")
public class ApproveAdmissions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveAdmissions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String approval = request.getParameter("approval");
		String message;
		int admisId = Integer.parseInt(request.getParameter("admisId"));
		Hpendingadmission admission = DBUtil.find(admisId, Hpendingadmission.class);
		if(approval.equals("accept")){
			Admin.acceptAdmission(admission);
			
			message = "<div class=\"alert alert-success\">"
			  +"<strong>Accepted</strong> You have approved User id: "+ admission.getUserid() +"</div>";;
		}
		else {
			message = "<div class=\"alert alert-danger\">"
					  +"<strong>Denied</strong> You have declined User id: "+ admission.getUserid() +"</div>";;
		}
		Admin.deletePending(admission);
		request.setAttribute("alert", message);
		getServletContext().getRequestDispatcher("/AdminPendingRequest").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	

}
