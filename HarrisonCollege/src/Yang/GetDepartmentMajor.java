package Yang;
import java.io.IOException;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Hclass;
import model.Hcours;
import model.Hmajor;
import model.Huser;
import customTools.DBUtil;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetDepartmentMajor")
public class GetDepartmentMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetDepartmentMajor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		//Huser user = (Huser) session.getAttribute("User");
		String fullList = "";
		//String currentYear = (String) session.getAttribute("currentYear");
		//String currentSemester = (String) session.getAttribute("currentSemester");
		long departmentId = Long.parseLong(request.getParameter("departmentId"));
		String alert = "";
		
		//Use the department ID
		TypedQuery<Hmajor> q = DBUtil.createQuery("SELECT h FROM Hmajor h where h.hdepartment.departmentId = ?1",Hmajor.class)
				.setParameter(1, departmentId);
		List<Hmajor> majorList;
		if(q.getResultList().isEmpty()){
			alert = "No Major in this department!";
		}else{
			majorList = q.getResultList();
			fullList = "<table class=\"table table-hover\"><thead><tr>"
					+ "<th>Major</th>"
					+ "<th>Department</th>"
					+ "<th>Enabled</th>"
					+ "</tr></thead><tbody>";
			for(int i=0;i<majorList.size();i++){
				fullList += "<tr><td>"+majorList.get(i).getName()
						 +"</td><td>"+majorList.get(i).getHdepartment().getName()
						 +"</td><td>"+majorList.get(i).getEnabled()
						 +"</td></tr>";
				}
			fullList += "</tbody></table>";
			}
			

		// Set response content type
		response.setContentType("text/html");

		request.setAttribute("fullList", fullList);
		request.setAttribute("alert", alert);

		getServletContext().getRequestDispatcher("/ViewList.jsp").forward(
				request, response);
		fullList = "";

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}