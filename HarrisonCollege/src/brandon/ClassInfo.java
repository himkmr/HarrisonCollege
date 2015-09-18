package brandon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;
import model.*;

/**
 * Servlet implementation class ClassInfo
 */
@WebServlet("/ClassInfo")
public class ClassInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setAttribute("classId",request.getParameter("id"));
		String id = "";
		System.out.println("Class id " +request.getAttribute("classId"));
		if(request.getAttribute("classId")==null){
			System.out.println("PARAMETER");
			id=request.getParameter("id");
		}
		else{
			System.out.println("ATTRIBUTE");
			id=(String)request.getAttribute("classId");
		}
		Hclass thisClass = DBUtil.find(Long.parseLong(id), Hclass.class);
		String classInfo = displayClassInfo(thisClass);
		String students = Display.displayStudents(Admin.studentsInClass(thisClass));
		String instructor = "";
		if(thisClass.getHofficial()==null){
			System.out.println("NO Official");
			request.setAttribute("hasInstructor",false);
		}
		else{
		 request.setAttribute("hasInstructor", true);
		 instructor = displayInstructor(thisClass.getHofficial());
		}
		request.setAttribute("classId",thisClass.getClassId());
		request.setAttribute("students",students);
		request.setAttribute("instructor",instructor);
		request.setAttribute("classInfo",classInfo);
		request.setAttribute("instructorList",instructorList());
		getServletContext().getRequestDispatcher("/ClassInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String instructorId = request.getParameter("selInstructor");
		String classId = request.getParameter("classId");
		Hofficial instructor = DBUtil.find(Long.parseLong(instructorId),Hofficial.class);
		Hclass hclass = DBUtil.find(Long.parseLong(classId),Hclass.class);
		request.setAttribute("classId",classId);
		Admin.addInstructorToClass(hclass, instructor);
		doGet(request,response);
		}


	protected static String displayClassInfo(Hclass hclass){
		StringBuilder display = new StringBuilder();
		List<Hclass> classes = new ArrayList<Hclass>();
		classes.add(hclass);
		return Display.displayClasses(classes);
	}
	
	protected static String displayInstructor(Hofficial official){
		List<Hofficial> instructor = new ArrayList<Hofficial>(); 
		instructor.add(official);
		return Display.displayInstructors(instructor);
	}
	
	protected static String instructorList(){
		StringBuilder display = new StringBuilder();
		for(Hofficial i : Admin.getAllInstructors()){
			display.append("<option value =\"" + i.getOfficialId() + "\">"+i.getHuser().getName()+"</option>");
		}
		return display.toString();
	}
}
