package Meenu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Hclass;
import model.Hclassenrollment;
import model.Huser;
import customTools.DBUtil;


/**
 * Servlet implementation class student_self_enrollment
 */
@WebServlet("/student_self_enrollment")
public class student_self_enrollment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public student_self_enrollment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Huser user = (Huser) session.getAttribute("User");
		String fullList = "";
		long studentID=0;
		if(user.getPermissions().equalsIgnoreCase("advisor")){
			studentID = Long.parseLong(request.getParameter("studentID"));
		}
		else
		{
			studentID = user.getUserId();
		}
		String currentYear = (String) session.getAttribute("currentYear");
		String currentSemester = (String) session.getAttribute("currentSemester");
		String alert = "";
		String classId = request.getParameter("classID");
		int stime = Integer.parseInt(request.getParameter("stime"));
		int etime = Integer.parseInt(request.getParameter("etime"));
		int rcap = Integer.parseInt(request.getParameter("rcap"));
		String checkcap = "";
		String checkenrolled = "";
		String timecheck = "";
		Hclassenrollment student = new Hclassenrollment();
		
//check capacity	
	//Get this student's enrollment(enrolled = yes)
		//check if the class is currently enrolled
		
		//check day and time
		//if pass
			//check if the class already exist(enrolled = no)
				//if exists-->update
				//if not exists-->add
		
		
		//condition 1 : Check capacity
		TypedQuery<Long> q = DBUtil.createQuery("SELECT count(h) FROM Hclassenrollment h where h.hclass.classId = ?1", Long.class)
				.setParameter(1, Long.parseLong(classId));
		Long count = q.getSingleResult();
		if(rcap-count>0){
			 checkcap = "pass";
		}else{
			 checkcap = "fail";
		}
		
		//condition 2: check if previously enrolled
		
		TypedQuery<Hclassenrollment> q2 = DBUtil.createQuery("SELECT h FROM Hclassenrollment h where h.hstudent.studentId = ?1 and h.hclass.classId = ?2", Hclassenrollment.class)
				.setParameter(1, studentID).setParameter(2, Long.parseLong(classId));
		if(!q2.getResultList().isEmpty()){
			if(q2.getSingleResult().getEnrolled().equalsIgnoreCase("yes")){
				 checkenrolled = "currentlyEnrolled";
			}else{
				 checkenrolled = "previouslyEnrolled";
			}
			
		}else{
			 checkenrolled = "neverEnrolled";
		}
		
		//condition 3: check day and time
		
		TypedQuery<Hclassenrollment> q3 = DBUtil.createQuery("SELECT h FROM Hclassenrollment h where h.hstudent.studentId = ?1 and h.enrolled = 'yes'", Hclassenrollment.class)
				.setParameter(1, studentID);
		if(q3.getResultList().isEmpty()){
			 timecheck = "pass";
		}else{
			List<Hclassenrollment> stuEnrollments = q3.getResultList();
			for(int i=0;i<stuEnrollments.size();i++){
				List<String> thisDow = getDay(stuEnrollments.get(i).getHclass().getDay());
				for(int j=0;j<thisDow.size();j++){
					int dbstime = Integer.parseInt(stuEnrollments.get(i).getHclass().getStarttime());
					int dbetime = Integer.parseInt(stuEnrollments.get(i).getHclass().getEndtime());
					Hclass thisClass = Student.getClass(classId);
					int thisStime = Integer.parseInt(thisClass.getStarttime());
					int thisEtime = Integer.parseInt(thisClass.getEndtime());
					List<String> thisClassDays = getDay(thisClass.getDay());
					for(int z=0;z<thisClassDays.size();z++){
						System.out.println(thisClassDays.get(z));
						System.out.println(thisDow.get(j));
						if((thisClassDays.get(z)).equalsIgnoreCase(thisDow.get(j))){
							if(thisEtime<=dbstime || thisStime>=dbetime){
								 timecheck = "pass";
							}else{
								 timecheck = "fail";
							}
							}	
						}
					}
				}
			}
		
		//1. Update
		
		System.out.println(checkcap+","+checkenrolled+","+timecheck);
		if(checkcap.equals("pass") && checkenrolled.equals("previouslyEnrolled") && timecheck.equals("pass")){
			Student.enrollAgain(studentID, classId);
		}
		
		//2. Add
		if(checkcap.equals("pass") && checkenrolled.equals("neverEnrolled") && timecheck.equals("pass")){
			student.setEnrolled("yes");
			student.setGrade("W");
			student.setHclass(Student.getClass(classId));
			student.setHstudent(Student.getStudent(studentID));
			Student.addClass(student);
			System.out.println("Added student");
		}
		if(user.getPermissions().equalsIgnoreCase("advisor")){
			
			getServletContext().getRequestDispatcher("/GetStudentInfo").forward(
					request, response);
		}
		else{
			getServletContext().getRequestDispatcher("/GetCurrentSchedule").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public static List<String> getDay(String days){
		List<String> dow = new ArrayList<String>();
		for(int i=0;i<days.length();i++){
			char c = days.charAt(i);
			dow.add(i, Character.toString(c));
		}
		return dow;
	}
}
