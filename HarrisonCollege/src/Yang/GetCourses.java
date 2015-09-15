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
import model.Hdepartment;
import model.Hmajor;
import model.Huser;
import customTools.DBUtil;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetCourses")
public class GetCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCourses() {
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
		String action = request.getParameter("action");
		String fullList = "";
		String currentYear = (String) session.getAttribute("currentYear");
		String currentSemester = (String) session.getAttribute("currentSemester");
		//currentYear="2014";
		//currentSemester="fall";
		String alert="";
		
	//1. Get All Courses
			if(action.equalsIgnoreCase("GetAllCourses")){
				
				TypedQuery<Hcours> q = DBUtil.createQuery("SELECT h FROM Hcours h",Hcours.class);
				List<Hcours> courseList;
				if(q.getResultList().isEmpty()){
					alert = "No course in database!";
				}else{
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
								 +"</td><td>"+courseList.get(i).getHdepartment().getName()+"</td></tr>";
					}
					fullList += "</tbody></table>";
				}
	//2. Get Current Class			
			}else if(action.equalsIgnoreCase("GetCurrentClass")){
				TypedQuery<Hclass> q = DBUtil.createQuery("SELECT h FROM Hclass h where h.semester = ?1 and h.year = ?2",Hclass.class)
						.setParameter(1, currentSemester).setParameter(2, currentYear);
				List<Hclass> classList;
				if(q.getResultList().isEmpty()){
					alert = "You don't have any class for now!";
				}else{
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
				}
	//3. Get current class of a specific subject			
			}else if(action.equalsIgnoreCase("GetCurrentClassSubject")){
				TypedQuery<Hcours> q = DBUtil.createQuery("SELECT h.hcours FROM Hclass h where h.semester = ?1 and h.year = ?2",Hcours.class)
						.setParameter(1, currentSemester).setParameter(2, currentYear);
				List<Hcours> courseList;
				if(q.getResultList().isEmpty()){
					alert = "No course in database!";
				}else{
					courseList = q.getResultList();
				fullList = "<ul>";
				for(int i=0;i<courseList.size();i++){
					fullList +="<li><a href=\"GetSubjectClass?subjectName="+courseList.get(i).getSubject().replace(" ", "%20")+"\">"+courseList.get(i).getSubject()+"</a></li>";
				}
				fullList += "</ul>";
				}
	//4. Get current classes by instructor			
			}else if(action.equalsIgnoreCase("GetCurrentClassInstructor")){
				TypedQuery<String> q = DBUtil.createQuery("SELECT distinct (h.hofficial.huser.name) FROM Hclass h where h.semester = ?1 and h.year = ?2",String.class)
						.setParameter(1, currentSemester).setParameter(2, currentYear);
				List<String> instructorList;
				if(q.getResultList().isEmpty()){
					alert = "No instructor in database!";
				}else{
					instructorList = q.getResultList();
				fullList = "<ul>";
				for(int i=0;i<instructorList.size();i++){
					fullList +="<li><a href=\"GetInstructorClass?instructorName="+instructorList.get(i)+"\">"+instructorList.get(i)+"</a></li>";
				}
				fullList += "</ul>";
				
				}
	//5. Get classes at a specific time
			}else if(action.equalsIgnoreCase("GetClassSpecificTime")){
				String startTime = request.getParameter("starttime");
				String endTime = request.getParameter("endtime");
				TypedQuery<Hclass> q = DBUtil.createQuery("SELECT h FROM Hclass h where h.hclass.semester = ?1 and h.hclass.year = ?2 and h.hclass.starttime = ?3 and h.hclass.endtime = ?4",Hclass.class)
						.setParameter(1, currentSemester).setParameter(2, currentYear).setParameter(3, startTime).setParameter(4, endTime);
				List<Hclass> classList;
				if(q.getResultList().isEmpty()){
					alert = "You don't have any class for the criteria!";
				}else{
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
			}
	//6. Get department list
		}else if(action.equalsIgnoreCase("GetCourseDepartment")){
			TypedQuery<String> q = DBUtil.createQuery("SELECT distinct (h.hdepartment.name) FROM Hcours h",String.class);
			List<String> deptList;
			if(q.getResultList().isEmpty()){
				alert = "No department in database!";
			}else{
				deptList = q.getResultList();
			fullList = "<ul>";
			for(int i=0;i<deptList.size();i++){
				fullList +="<li><a href=\"GetDepartmentCourse?departmentName="+deptList.get(i)+"\">"+deptList.get(i)+"</a></li>";
			}
			fullList += "</ul>";
		}
	//7. Get current classes from a department (Only the student can see the classes of a department so that they can log in)
			}else if(action.equalsIgnoreCase("GetClassDepartment")){
				TypedQuery<String> q = DBUtil.createQuery("SELECT distinct (h.hcours.hdepartment.name) FROM Hclass h where h.semester = ?1 and h.year = ?2",String.class)
						.setParameter(1, currentSemester).setParameter(2, currentYear);
				List<String> deptList;
				if(q.getResultList().isEmpty()){
					alert = "No department in database!";
				}else{
					deptList = q.getResultList();
				fullList = "<ul>";
				for(int i=0;i<deptList.size();i++){
					fullList +="<li><a href=\"GetDepartmentClass?departmentName="+deptList.get(i)+"\">"+deptList.get(i)+"</a></li>";
				}
				fullList += "</ul>";
			}

	//8. Get all majors in a department		
		}else if(action.equalsIgnoreCase("GetMajorDepartment")){
			TypedQuery<Hdepartment> q = DBUtil.createQuery("SELECT h FROM Hdepartment h",Hdepartment.class);
			List<Hdepartment> deptList;
			if(q.getResultList().isEmpty()){
				alert = "No department in database!";
			}else{
				deptList = q.getResultList();
			fullList = "<ul>";
			for(int i=0;i<deptList.size();i++){
				fullList +="<li><a href=\"GetDepartmentMajor?departmentId="+deptList.get(i).getDepartmentId()+"\">"+deptList.get(i).getName()+"</a></li>";
			}
			fullList += "</ul>";
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

}