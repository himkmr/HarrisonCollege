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
import model.Huser;
import customTools.DBUtil;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetDepartmentCourse")
public class GetDepartmentCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetDepartmentCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Huser user = (Huser) session.getAttribute("User");
		String fullList = "";
		//String currentYear = (String) session.getAttribute("currentYear");
		//String currentSemester = (String) session.getAttribute("currentSemester");
		String departmentName = request.getParameter("departmentName");
		String alert = "";
		
		TypedQuery<Hcours> q = DBUtil.createQuery("SELECT h FROM Hcours h where h.hdepartment.name = ?1",Hcours.class)
				.setParameter(1, departmentName);
		List<Hcours> courseList;
			courseList = q.getResultList();
			fullList = "<table class=\"table table-hover\"><thead><tr>"
					+ "<th>Course ID</th>"
					+ "<th>Subject</th>"
					+ "<th>Credit Hours</th>"
					+ "<th>Department</th>"
					+ "</tr></thead><tbody>";
			for(int i=0;i<courseList.size();i++){
				fullList += "<tr><td>"+courseList.get(i).getCourseId()
						 +"</td><td>"+courseList.get(i).getSubject()
						 +"</td><td>"+courseList.get(i).getCreditHours()
						 +"</td><td>"+courseList.get(i).getHdepartment().getName()+"</td>";
			}
			fullList += "</tbody></table>";

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