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
import model.Huser;
import customTools.DBUtil;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetInstructorClass")
public class GetInstructorClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetInstructorClass() {
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
		String currentYear = (String) session.getAttribute("currentYear");
		String currentSemester = (String) session.getAttribute("currentSemester");
		String instructorName = request.getParameter("instructorName");
		String alert = "";
		

	// Get Current Class in a subject			
				TypedQuery<Hclass> q = DBUtil.createQuery("SELECT h FROM Hclass h where h.semester = ?1 and h.year = ?2 and h.hofficial.huser.name = ?3",Hclass.class)
						.setParameter(1, currentSemester).setParameter(2, currentYear).setParameter(3, instructorName);
				List<Hclass> classList;
					classList = q.getResultList();
					fullList = "<table class=\"table table-hover\"><thead><tr>"
							+ "<th>Course</th>"
							+ "<th>Instructor</th>"
							+ "<th>Class Room</th>"
							+ "<th>Semester</th>"
							+ "<th>Year</th>"
							+ "<th>Day</th>"
							+ "<th>Start Time</th>"
							+ "<th>End Time</th>"
							+ "<th>Enabled</th>"
							+ "</tr></thead><tbody>";
					for(int i=0;i<classList.size();i++){
						fullList += "<tr><td>"+classList.get(i).getHcours().getSubject()
								 +"</td><td>"+classList.get(i).getHofficial().getHuser().getName()
								 +"</td><td>"+classList.get(i).getHclassroom().getBuilding()+"\t"+classList.get(i).getHclassroom().getRoomNumber()
								 +"</td><td>"+classList.get(i).getSemester()
								 +"</td><td>"+classList.get(i).getYear()
								 +"</td><td>"+classList.get(i).getDay()
								 +"</td><td>"+classList.get(i).getStarttime()
								 +"</td><td>"+classList.get(i).getEndtime()
								 +"</td><td>"+classList.get(i).getEnabled()
								 +"</td></tr>";
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