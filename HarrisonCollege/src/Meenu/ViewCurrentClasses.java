package Meenu;

import java.io.IOException;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;
import model.Hclass;
import model.Hclassenrollment;

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
    	String studentID = "2";
    	String alert = "";
    	String fullList = "";
    	String q = "SELECT h FROM Hclassenrollment h where h.hstudent=?1 and h.enrolled = 'yes'";
		TypedQuery<Hclass> bq = DBUtil.createQuery(q, Hclass.class).setParameter(1, Student.getStudent(studentID));
		List<Hclass> classList;
		if(bq.getResultList().isEmpty()){
			alert = "No current class!";
		}else{
			classList=bq.getResultList();
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
					 +"</td><td>"+getTime(classList.get(i).getStarttime())
					 +"</td><td>"+getTime(classList.get(i).getEndtime())
					 +"</td><td>"+classList.get(i).getEnabled()+"</td>";
			//if(user.getPermissions().equalsIgnoreCase("student")){
				fullList += "<td><a href=\"DropClass?classID="
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
