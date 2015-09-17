package Yang;
import Meenu.*;
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
 * Servlet implementation class AddComment
 */
@WebServlet("/StudentSelfEnroll")
public class StudentSelfEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentSelfEnroll() {
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
		String alert = "";
		String classId = request.getParameter("classID");
		int stime = Integer.parseInt(request.getParameter("stime"));
		int etime = Integer.parseInt(request.getParameter("etime"));
		int rcap = Integer.parseInt(request.getParameter("rcap"));

		
//check capacity	
	//Get this student's enrollment(enrolled = yes)
		//check if the class is currently enrolled
		
		//check day and time
		//if pass
			//check if the class already exist(enrolled = no)
				//if exists-->update
				//if not exists-->add
		
			TypedQuery<Integer> q = DBUtil.createQuery("SELECT count(h) FROM Hclassenrollment h where h.hclass.classId = ?1", Integer.class)
					.setParameter(1, classId);
			int count = q.getSingleResult();
			if(rcap-count>0){
				TypedQuery<Hclassenrollment> q2 = DBUtil.createQuery("SELECT h FROM Hclassenrollment h where h.hstudent.huser = ?1 and h.hclass.classId = ?2", Hclassenrollment.class)
						.setParameter(1, user).setParameter(2, classId);
				if(q2.getSingleResult()!=null){
					if(q2.getSingleResult().getEnrolled().equalsIgnoreCase("yes")){
						System.out.println("You are currently enrolled in this class");
					}else{
						TypedQuery<Hclassenrollment> q3 = DBUtil.createQuery("SELECT h FROM Hclassenrollment h where h.hstudent.huser = ?1 and h.enrolled = 'yes'", Hclassenrollment.class)
								.setParameter(1, user);
						if(q3.getResultList().isEmpty()){
							Student.enrollAgain(user.getUserId(), classId);
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
										if(thisClassDays.get(z)==thisDow.get(j)){
											if(thisEtime<=dbstime || thisStime>=dbetime){
												Student.enrollAgain(user.getUserId(), classId);
											}else{
												System.out.println("You have time conflict");
											}
										}
									}
								}
							}
						}
					}
				}else{
					//Not ever enrolled in this class
					TypedQuery<Hclassenrollment> q5 = DBUtil.createQuery("SELECT h FROM Hclassenrollment h where h.hstudent.huser = ?1 and h.enrolled = 'yes'", Hclassenrollment.class)
							.setParameter(1, user);
					if(q5.getResultList().isEmpty()){
						//Add Enrollment
					}else{
						List<Hclassenrollment> stuEnrollments = q5.getResultList();
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
									if(thisClassDays.get(z)==thisDow.get(j)){
										if(thisEtime<=dbstime || thisStime>=dbetime){
											//Add enrollment
										}else{
											System.out.println("You have time conflict");
										}
									}
								}
							}
						}
					}
				}
			}else{
				System.out.println("class reaches max capacity");
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
	
	
	public static List<String> getDay(String days){
		List<String> dow = new ArrayList<String>();
		for(int i=0;i<days.length();i++){
			char c = days.charAt(i);
			dow.add(i, Character.toString(c));
		}
		return dow;
	}

}