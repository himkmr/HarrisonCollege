package himanshu;

import java.io.IOException;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Htuitionfee;
import customTools.DBUtil;

/**
 * Servlet implementation class ChangeFee
 */
@WebServlet("/ChangeFee")
public class ChangeFee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeFee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String changesemester = request.getParameter("changesemester");
		String changeyear = request.getParameter("changeyear");
		String fee = request.getParameter("fee");
		String q = "select t from Htuitionfee t where t.semester=:semester and t.year=:year";
		TypedQuery<Htuitionfee> tq = DBUtil.createQuery(q, Htuitionfee.class).setParameter("semester", changesemester).setParameter("year", changeyear);
		Htuitionfee fee_obj = tq.getSingleResult();
		fee_obj.setPerCreditTuition(fee);
		DBUtil.updateDB(fee_obj);	
		
		String message = "<div align=\"center\" >Fee Successfully Updated!";
		request.setAttribute("message", message);
		request.getServletContext().getRequestDispatcher("/i_output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
