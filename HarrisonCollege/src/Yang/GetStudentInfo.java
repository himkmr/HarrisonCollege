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
import model.Hstudent;
import model.Huser;
import customTools.DBUtil;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetStudentInfo")
public class GetStudentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetStudentInfo() {
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
		//String instructorName = request.getParameter("instructorName");
		String alert = "";
		

	// Get Current Class in a subject			
				TypedQuery<Hstudent> q = DBUtil.createQuery("SELECT h FROM Hstudent h",Hstudent.class);
				List<Hstudent> classList;
				if(q.getResultList().isEmpty()){
					alert="No student in database!";
				}else{
					classList = q.getResultList();
					fullList = "<table class=\"table table-hover\"><thead><tr>"
							+ "<th>Student ID</th>"
							+ "<th>Student Name</th>"
							+ "<th>Major</th>"
							+ "<th>Entry Year</th>"
							+ "<th>Other</th>"
							+ "</tr></thead><tbody>";
					for(int i=0;i<classList.size();i++){
						fullList += "<tr><td>"+classList.get(i).getStudentId()
								 +"</td><td>"+classList.get(i).getHuser().getName()
								 +"</td><td>"+classList.get(i).getMajor().getName()
								 +"</td><td>"+classList.get(i).getEntryYear()
								  +"</td><td><a href=\"GetThisTranscript?studentId="
								 +classList.get(i).getStudentId()
								 +"\">Transcript</a><br><a href=\"EnrollThisStudent?studentId="
								 +classList.get(i).getStudentId()
								 +"\">Enroll</a><br><a href=\"DropThisStudent?studnetId="
								 +classList.get(i).getStudentId()
								 +"\">Drop</a>"
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