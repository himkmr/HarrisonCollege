package himanshu;

import java.io.IOException;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hclass;
import model.Huser;
import customTools.DBUtil;

/**
 * Servlet implementation class ViewGradeSheets
 */
@WebServlet("/ViewGradeSheets")
public class ViewGradeSheets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewGradeSheets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		model.Huser user = (Huser) request.getSession().getAttribute("User");
		long userid = user.getUserId();
		String q = "select distinct t.semester from Hclass t where t.hofficial.officialId=:officialid";	
		TypedQuery<String> tq = DBUtil.createQuery(q, String.class).setParameter("officialid", userid);		
		List<String> list = tq.getResultList();
		String sarray[] = new String[5];
		String yarray[] = new String[5];
		
		int index = 0;
		for(String temp:list)
		{
			sarray[index] = temp;
			index++;
		}
	
		String q2 = "select distinct t.year from Hclass t where t.hofficial.officialId=:officialid";	
		TypedQuery<String> tq2 = DBUtil.createQuery(q2, String.class).setParameter("officialid", userid);		
		List<String> list2 = tq2.getResultList();
		index = 0;
		for(String temp:list2)
		{
			yarray[index] = temp;
			index++;
		}
		request.setAttribute("sarray", sarray);
		request.setAttribute("yarray", yarray);
		request.getServletContext()
		.getRequestDispatcher("/ViewGradeSheetForm.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
