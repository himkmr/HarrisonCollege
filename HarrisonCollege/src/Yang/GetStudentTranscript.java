package Yang;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Hclass;
import model.Hclassenrollment;
import model.Hcours;
import model.Hmajor;
import model.Hstudent;
import model.Huser;
import customTools.DBUtil;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetStudentTranscript")
public class GetStudentTranscript extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetStudentTranscript() {
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
		String alert="";
		
		if(!user.getPermissions().equalsIgnoreCase("student")){
				alert = "Please log in as a student...";
		}else{
			TypedQuery<Hstudent> q1 = DBUtil.createQuery("SELECT h FROM Hstudent h where h.huser = ?1",Hstudent.class).setParameter(1, user);
			Hstudent stu = new Hstudent();
			stu = q1.getSingleResult();
			TypedQuery<String> q2 = DBUtil.createQuery("SELECT distinct (h.hclass.year) FROM Hclassenrollment h where h.hstudent = ?1 order by h.hclass.year desc",String.class).setParameter(1, stu);
			List<String> yearList;
			if(q2.getResultList().isEmpty()){
				alert = "You don't have any transcript history.";
			}else{
				yearList = q2.getResultList();
				for(int i=0;i<yearList.size();i++){
					double totalGrade = 0;
					int countCreditHours = 0;
					String thisYear = yearList.get(i);
					TypedQuery<Hclassenrollment> q3 = DBUtil.createQuery("SELECT h FROM Hclassenrollment h where h.hclass.year = ?1 and h.hclass.semester = ?2 and h.hstudent = ?3 and h.enrolled = ?4",Hclassenrollment.class)
							.setParameter(1, thisYear).setParameter(2, "fall").setParameter(3, stu).setParameter(4, "yes");
					if(!q3.getResultList().isEmpty()){
						List<Hclassenrollment> theseEnrollments = q3.getResultList();
						fullList += "<h4>"+thisYear+"	"+"Fall</h4><br>"
								 +"<table class=\"table table-hover\"><thead><tr>"
								 + "<th>Course</th>"
								 + "<th>Grade</th>"
								 + "<th>Credit Hours</th>"
								 + "</tr></thead><tbody>";
						for(int j=0;j<theseEnrollments.size();j++){
								fullList += "<tr><td>"+theseEnrollments.get(j).getHclass().getHcours().getSubject()
										 +"</td><td>"+theseEnrollments.get(j).getGrade()
										 +"</td><td>"+theseEnrollments.get(j).getHclass().getHcours().getCreditHours()
										 +"</td></tr>";
								totalGrade += getScore(theseEnrollments.get(j).getGrade()) * theseEnrollments.get(j).getHclass().getHcours().getCreditHours();
								countCreditHours += theseEnrollments.get(j).getHclass().getHcours().getCreditHours();
						}
						fullList += "</tbody></table>";
						fullList += "<p>GPA: "+totalGrade/countCreditHours+"</p>";
					}
					totalGrade = 0;
					countCreditHours = 0;
					TypedQuery<Hclassenrollment> q4 = DBUtil.createQuery("SELECT h FROM Hclassenrollment h where h.hclass.year = ?1 and h.hclass.semester = ?2 and h.hstudent = ?3 and h.enrolled = ?4",Hclassenrollment.class)
							.setParameter(1, thisYear).setParameter(2, "spring").setParameter(3, stu).setParameter(4, "yes");
					if(!q4.getResultList().isEmpty()){
						List<Hclassenrollment> theseEnrollments = q4.getResultList();
						fullList += "<h4>"+thisYear+"	"+"Spring</h4><br>"
								 +"<table class=\"table table-hover\"><thead><tr>"
								 + "<th>Course</th>"
								 + "<th>Grade</th>"
								 + "<th>Credit Hours</th>"
								 + "</tr></thead><tbody>";
						for(int j=0;j<theseEnrollments.size();j++){
								fullList += "<tr><td>"+theseEnrollments.get(j).getHclass().getHcours().getSubject()
										 +"</td><td>"+theseEnrollments.get(j).getGrade()
										 +"</td><td>"+theseEnrollments.get(j).getHclass().getHcours().getCreditHours()
										 +"</td></tr>";
								totalGrade += getScore(theseEnrollments.get(j).getGrade()) * theseEnrollments.get(j).getHclass().getHcours().getCreditHours();
								countCreditHours += theseEnrollments.get(j).getHclass().getHcours().getCreditHours();
						}
						fullList += "</tbody></table>";
						fullList += "<p>GPA: "+totalGrade/countCreditHours+"</p>";
					}
				}
				fullList += "<a href=\"BuyTranscript.jsp\">Buy</a>";
			}
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
	
	public int getScore(String grade){
		int gradeInt = 0;
		if(grade.equalsIgnoreCase("A")){
			gradeInt = 95;
		}else if(grade.equalsIgnoreCase("B")){
			gradeInt = 85;
		}else if(grade.equalsIgnoreCase("C")){
			gradeInt = 75;
		}else if(grade.equalsIgnoreCase("D")){
			gradeInt = 65;
		}else if(grade.equalsIgnoreCase("E")){
			gradeInt = 55;
		}else if(grade.equalsIgnoreCase("W")){
			gradeInt=0;
		}
		return gradeInt;
	}

}