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
@WebServlet("/GetDepartmentClass")
public class GetDepartmentClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetDepartmentClass() {
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
		String currentYear = (String) session.getAttribute("currentYear");
		String currentSemester = (String) session.getAttribute("currentSemester");
		String departmentName = request.getParameter("departmentName");
		String alert = "";
		//Use the department name
		TypedQuery<Hclass> q = DBUtil.createQuery("SELECT h FROM Hclass h where h.hcours.hdepartment.name = ?1 and h.semester = ?2 and h.year = ?3",Hclass.class)
				.setParameter(1, departmentName).setParameter(2, currentSemester).setParameter(3, currentYear);
		List<Hclass> classList;
		if(q.getResultList().isEmpty()){
			alert = "No current class!";
		}else{
			classList=q.getResultList();
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
					+ "<th>Other</th>"
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
						 +"</td><td>"+classList.get(i).getEnabled()+"</td>";
				if(user.getPermissions().equalsIgnoreCase("student")){
					fullList += "<td><a href=\"Classenrollment?classID="
							 +classList.get(i).getClassId()
							 +"&rcap="+classList.get(i).getHclassroom().getCapacity()
							 +"&stime="+classList.get(i).getStarttime()
							 +"&etime="+classList.get(i).getEndtime()+"\">Enroll</a></td>";
				}
				fullList += "</tr>";
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