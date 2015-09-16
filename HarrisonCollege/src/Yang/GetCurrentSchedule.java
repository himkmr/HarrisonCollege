package Yang;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
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
@WebServlet("/GetCurrentSchedule")
public class GetCurrentSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCurrentSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Huser user = (Huser) session.getAttribute("User");
		String currentYear = (String) session.getAttribute("currentYear");
		String currentSemester = (String) session.getAttribute("currentSemester");
		
		
		String weekday = null;
		String alert = "";
		String fullList = null;
		// For instructor
		if (user.getPermissions().equalsIgnoreCase("student")) {
			// Get Current Class
			TypedQuery<Hclass> q = DBUtil.createQuery("SELECT h.hclass FROM Hclassenrollment h where h.hstudent.huser = ?1 and h.hclass.year = ?2 and h.hclass.semester = ?3",Hclass.class)
					.setParameter(1, user)
					.setParameter(2, currentYear)
					.setParameter(3, currentSemester);
			fullList = "<table class=\"table table-hover\"><tbody>";
			if (q.getResultList().isEmpty()) {
				alert = "You don't have any class for now!";
			} else {
				for (int i = 0; i < 5; i++) {
					if (i == 0) {
						weekday = "M";
					} else if (i == 1) {
						weekday = "T";
					} else if (i == 2) {
						weekday = "W";
					} else if (i == 3) {
						weekday = "H";
					} else if (i == 4) {
						weekday = "F";
					}
					fullList += "<tr><td>"+weekday+"</td>";
					TypedQuery<Hclass> q2 = DBUtil
							.createQuery(
									"SELECT h.hclass FROM Hclassenrollment h where h.hstudent.huser = ?1 and h.hclass.year = ?2 and h.hclass.semester = ?3 and h.hclass.day like ?4",
									Hclass.class).setParameter(1, user)
							.setParameter(2, currentYear)
							.setParameter(3, currentSemester)
							.setParameter(4, "%" + weekday + "%");
					List<Hclass> thisClassList;
					if (!q2.getResultList().isEmpty()) {
						thisClassList = q2.getResultList();
						Collections.sort(thisClassList, ClassTime);
						for(int j=0;j<thisClassList.size();j++){
							fullList +="<td>"+thisClassList.get(j).getHcours().getSubject()+"<br>"
									 +getTime(thisClassList.get(j).getStarttime())
									 +"-"
									 +getTime(thisClassList.get(j).getEndtime())+"</td>";
						}
						fullList += "</tr>";
					}else{
						fullList += "</tr>";
					}
				}
				fullList += "</tbody></table>";
				System.out.println(fullList);
			}
		} else {
			alert = "Please log in as a student...";
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
	
	public static Comparator<Hclass> ClassTime = new Comparator<Hclass>(){

		public int compare(Hclass c1, Hclass c2) {

			int timeC1 = Integer.parseInt(c1.getStarttime());
			int timeC2 = Integer.parseInt(c2.getStarttime());

			return timeC1 - timeC2;
		}
	};
	
	public String getTime(String time){
		String Stime = "";
		for(int i = 0; i < time.length(); i++)
		{
		   char c = time.charAt(i);
		   String starttime = Character.toString(c);
		  Stime += starttime; 
		   if (i==1)
			   Stime += ":";
		}
		return Stime;
	}
	
	

}