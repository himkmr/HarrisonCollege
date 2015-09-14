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
		Huser user = (Huser) session.getAttribute("User");
		String action = request.getParameter("action");
		String fullList = "";
		String currentYear = (String) session.getAttribute("currentYear");
		String currentSemester = (String) session.getAttribute("currentSemester");
		String alert="";
		
	//1. Get All Courses
			if(action.equalsIgnoreCase("GetAllCourses")){
				TypedQuery<Hcours> q = DBUtil.createQuery("SELECT h FROM Hcours h",Hcours.class);
				List<Hcours> courseList;
				if(q.getResultList().isEmpty()){
					alert = "You don't have any course!";
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
				String selectedSubject = request.getParameter("subject");
				TypedQuery<Hclass> q = DBUtil.createQuery("SELECT h FROM Hclass h where h.hclass.semester = ?1 and h.hclass.year = ?2 and h.hclass.hcours.subject = ?3",Hclass.class)
						.setParameter(1, currentSemester).setParameter(2, currentYear).setParameter(3, selectedSubject);
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
	//4. Get current classes by instructor			
			}else if(action.equalsIgnoreCase("GetCurrentClassInstructor")){
				//Use official ID 
				String selectedInstructor = request.getParameter("instructor");
				TypedQuery<Hclass> q = DBUtil.createQuery("SELECT h FROM Hclass h where h.hclass.semester = ?1 and h.hclass.year = ?2 and h.hclass.hofficial.officialId = ?3",Hclass.class)
						.setParameter(1, currentSemester).setParameter(2, currentYear).setParameter(3, selectedInstructor);
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
	//6. Get Courses from a department
		}else if(action.equalsIgnoreCase("GetCourseDepartment")){
			//Use the department ID
			String departmentID = request.getParameter("department");
			TypedQuery<Hcours> q = DBUtil.createQuery("SELECT h FROM Hcours h where h.hdepartment.departmentId = ?1",Hcours.class)
					.setParameter(1, departmentID);
			List<Hcours> courseList;
			if(q.getResultList().isEmpty()){
				alert = "You don't have any class for the criteria!";
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
	//7. Get all majors in a department		
		}else if(action.equalsIgnoreCase("GetMajorDepartment")){
			String departmentID = request.getParameter("department");
			TypedQuery<Hmajor> q = DBUtil.createQuery("SELECT h FROM Hmajor h where h.hdepartment.departmentId = ?1",Hmajor.class)
					.setParameter(1, departmentID);
			List<Hmajor> majorList;
			if(q.getResultList().isEmpty()){
				alert = "You don't have any class for the criteria!";
			}else{
				majorList = q.getResultList();
				fullList = "<table class=\"table table-hover\"><thead><tr>"
						+ "<th>Major ID</th>"
						+ "<th>Name</th>"
						+ "<th>Department</th>"
						+ "<th>Enabled</th>"
						+ "</tr></thead><tbody>";
				for(int i=0;i<majorList.size();i++){
					fullList += "<tr><td>"+majorList.get(i).getMajorId()
							 +"</td><td>"+majorList.get(i).getName()
							 +"</td><td>"+majorList.get(i).getHdepartment().getName()
							 +"</td><td>"+majorList.get(i).getEnabled()+"</td></tr>";
				}
				fullList += "</tbody></table>";
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