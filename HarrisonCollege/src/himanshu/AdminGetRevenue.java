package himanshu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hdepartment;
import customTools.DBUtil;

/**
 * Servlet implementation class AdminGetRevenue
 */
@WebServlet("/AdminGetRevenue")
public class AdminGetRevenue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminGetRevenue() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	//set the attributes department, semester, year to the request and pass it to the form
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		List<String> d_array = getDepartments();
		List<String> s_array = getSemesters();
		List<String> y_array = getYears();
		
		
		String currentyear = (String) request.getSession().getAttribute("currentYear");
		List<String> coming_years_array = getcomingYears(currentyear);
			
		request.setAttribute("departments", d_array);
		request.setAttribute("semesters", s_array);
		request.setAttribute("years",y_array );
		request.setAttribute("comingyears",coming_years_array );
		request.getServletContext()
		.getRequestDispatcher("/RevenueGeneratorForm.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	//Get All the Departments
	private List<String> getDepartments() {

		String q = "select t from Hdepartment t";
		TypedQuery<Hdepartment> tq = DBUtil.createQuery(q,
				model.Hdepartment.class);
		List<Hdepartment> dlist = tq.getResultList();
		if (dlist == null)
			return null;
		List <String> dlist_string =new ArrayList<String>();

		for (Hdepartment temp : dlist) {
			dlist_string.add(temp.getName());
		}

		return dlist_string;
	}
	
	//Get all the semesters
	private List<String> getSemesters() {
		String q = "select distinct t.semester from Hclass t";	
		TypedQuery<String> tq = DBUtil.createQuery(q, String.class);		
		List<String> list = tq.getResultList();
		if (list == null)
			return null;

		return list;
	}
	
	// Get all the  years
	
	private List<String> getYears() {

		String q2 = "select distinct t.year from Hclass t";	
		TypedQuery<String> tq2 = DBUtil.createQuery(q2, String.class);		
		List<String> list2 = tq2.getResultList();
		if (list2 == null)
			return null;
		
		return list2;
	}
	private List<String> getcomingYears(String cur_yr) {
		int curr_year = Integer.parseInt(cur_yr);
		String q2 = "select distinct t.year from Hclass t where t.year >= "+curr_year;	
		TypedQuery<String> tq2 = DBUtil.createQuery(q2, String.class);		
		List<String> list2 = tq2.getResultList();
		if (list2 == null)
			return null;
	
		return list2;
	}
	
	
}
