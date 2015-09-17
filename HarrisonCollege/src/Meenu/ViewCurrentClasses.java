package Meenu;

import java.io.IOException;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.Hclass;
import model.Hclassenrollment;
import model.Hstudent;
import model.Huser;

/**
 * Servlet implementation class ViewCurrentClasses
 */
@WebServlet("/ViewCurrentClasses")
public class ViewCurrentClasses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCurrentClasses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    
    	String alert = "";
    	String fullList = "";
    	 
    	HttpSession session = request.getSession(true);
    	Huser thisUser = (Huser) session.getAttribute("User");
		long studentID = thisUser.getHstudent().getStudentId();
    	//long studentID = 2;
    	String currentYear = (String) session.getAttribute("currentYear");
		String currentSemester = (String) session.getAttribute("currentSemester");	
    	//String currentYear = "2015";
    	//String currentSemester = "SPRING";
    	String q = "SELECT h FROM Hclassenrollment h where h.hstudent=?1 and h.enrolled = 'yes' and h.hclass.semester= ?2 and h.hclass.year = ?3";
		TypedQuery<Hclassenrollment> bq = DBUtil.createQuery(q, Hclassenrollment.class).setParameter(1, Student.getStudent(studentID)).setParameter(2, currentSemester).setParameter(3, currentYear);
		System.out.println("111");
		List<Hclassenrollment> classList = bq.getResultList();
		
		if(bq.getResultList().isEmpty()){
			alert = "No current class!";
		}else{
		fullList = "<table class=\"table table-hover\"><thead><tr>"
				+ "<th>Course</th>"
				+ "<th>Instructor</th>"
				+ "<th>Class Room</th>"
				+ "<th>Semester</th>"
				+ "<th>Year</th>"
				+ "<th>Day</th>"
				+ "<th>Class Time</th>"
				+ "<th>Enabled</th>"
				+ "<th>Other</th>"
				+ "</tr></thead><tbody>";
		for(Hclassenrollment temp : classList){
			fullList += "<tr><td>"+temp.getHclass().getHcours().getSubject()
					 +"</td><td>"+temp.getHclass().getHofficial().getHuser().getName()
					 +"</td><td>"+temp.getHclass().getHclassroom().getBuilding()+" "+temp.getHclass().getHclassroom().getRoomNumber()
					 +"</td><td>"+temp.getHclass().getSemester()
					 +"</td><td>"+temp.getHclass().getYear()
					 +"</td><td>"+temp.getHclass().getDay()
					 +"</td><td>"+getTime(temp.getHclass().getStarttime())+" -"+getTime(temp.getHclass().getEndtime())
					 +"</td><td>"+temp.getHclass().getEnabled()+"</td>";
			//if(user.getPermissions().equalsIgnoreCase("student")){
				fullList += "<td><a href=\"DropClass?classID="+temp.getHclass().getClassId()
						+"\">DROP</a></td>";
			}
			fullList += "</tr>";
			}
		fullList += "</tbody></table>";
		
		response.setContentType("text/html");

		request.setAttribute("fullList", fullList);
		request.setAttribute("alert", alert);

		getServletContext().getRequestDispatcher("/ViewList.jsp").forward(
				request, response);
		fullList = "";
		}
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
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
