package himanshu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetGradeSheet
 */
@WebServlet("/GetGradeSheet")
public class GetGradeSheet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGradeSheet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String semester =request.getParameter("semesters");
		String year =request.getParameter("years");
		model.Huser user = (model.Huser)request.getSession().getAttribute("User");
		long userid = user.getUserId();
		java.util.List <model.Hclass> class_list = ViewClasses.getAllClassesInSemester(userid, semester, year);
		String message ="";
		message += "<div class=\"container\" align=\"center\"><b>Classes in Current Semester</b>";
		message += "<table class=\"table table-hover\" align=\"center\"><tr bgcolor=\"#e6e6e6\"><td><b>Course Name</td><td><b>Class Id </td><td><b>Days</td><td><b>Start time</td><td><b>End time</td></tr>";
		for(model.Hclass temp: class_list)
		{
			message+= "<td>"+temp.getHcours().getSubject()+"</td>"+
					"<td><a href=\"GetStudentsInClass?classid="+temp.getClassId()+"\">"+temp.getClassId()+"</td>"+
					"<td>"+temp.getDay()+"</td>"+
					"<td>"+temp.getStarttime()+"</td>"+
					"<td>"+temp.getEndtime()+"</td></tr>";
		
		}			
		message += "</table>";
		message += "<div align=\"left\"><ul><li><b> <a href=\"ViewAllClassesofInst?userid="+userid+"\"><button type=\"button\" class=\"btn btn-primary\">View All Classes in current Semesters</button></a></li><b>";
		message += "<li><b> <a href=\"InstructorFirstPage\"><button type=\"button\" class=\"btn btn-primary\">View All Classes in All Semesters</button></a></li><b>";
		message += "<li><b><a href=\"ViewGradeSheets?userid="+userid+"\"><button type=\"button\" class=\"btn btn-primary\">View GradeSheets</button></a><b></li></ul>";
		request.setAttribute("message", message);
		request.getServletContext().getRequestDispatcher("/i_output.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
