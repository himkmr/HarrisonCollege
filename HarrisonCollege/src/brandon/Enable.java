package brandon;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import customTools.DBUtil;

/**
 * Servlet implementation class Enable
 */
@WebServlet("/Enable")
public class Enable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Enable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type= request.getParameter("type");
		long id = Long.parseLong(request.getParameter("id"));
		if(request.getParameter("todo").equals("enable")){
			enableRequest(type, id);
		}
		else{
			disableRequest(type, id);
		}
		switch(type){
		case("classroom"): request.setAttribute("select","classrooms"); break;
		case("course"): request.setAttribute("select","courses"); break;
		case("department"): request.setAttribute("select","departments"); break;
		case("major"): request.setAttribute("select","majors"); break;
		case("class"): request.setAttribute("select","classes"); break;
		}
		getServletContext().getRequestDispatcher("/AdminSearch").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected static void enableRequest(String type, long id){

		switch(type){
		case("classroom"): Admin.enableClassroom(DBUtil.find(id, Hclassroom.class)); break;
		case("course"): Admin.enableCourse(DBUtil.find(id,Hcours.class)); break;
		case("department"): Admin.enableDepartment(DBUtil.find(id, Hdepartment.class)); break;
		case("major"): Admin.enableMajor(DBUtil.find(id,Hmajor.class)); break;
		case("class"): Admin.enableClass(DBUtil.find(id,Hclass.class)); break;
		}
	}
	
	protected static void disableRequest(String type, long id){
		
		switch(type){
		case("classroom"): Admin.disableClassroom(DBUtil.find(id, Hclassroom.class)); break;
		case("course"): Admin.disableCourse(DBUtil.find(id,Hcours.class)); break;
		case("department"): Admin.disableDepartment(DBUtil.find(id, Hdepartment.class)); break;
		case("major"): Admin.disableMajor(DBUtil.find(id,Hmajor.class)); break;
		case("class"): Admin.disableClass(DBUtil.find(id,Hclass.class)); break;
		}
	}

}
