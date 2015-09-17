package himanshu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewAllClassesofInst
 */
@WebServlet("/ViewAllClassesofInst")
public class ViewAllClassesofInst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllClassesofInst() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	model.Huser user = (model.Huser) request.getSession().getAttribute(
			"User");
	long userid = user.getUserId();
	List<model.Hclass> classes_in_cur_sem = himanshu.ViewClasses.getAllClassAllSemesters(userid);

	String message = "";
	message += "<div class=\"container\" align=\"center\"><h4>All Classes </h4>";
	message += "<table class=\"table table-hover\"  align=\"center\"><tr bgcolor=\"#e6e6e6\"><td><b>Course Name</td><td><b>Class Id </td><td><b>Days</td><td><b>Start time</td><td><b>End time</td><td><b>Semester/Year</td></tr>";
	for (model.Hclass temp : classes_in_cur_sem) {
		message += "<td>" + temp.getHcours().getSubject() + "</td><td><a href=\"GetStudentsInClass?classid="+temp.getClassId()+"\">"
				+ temp.getClassId() + "</a></td><td>" + temp.getDay()
				+ "</td><td>" + temp.getStarttime() + "</td><td>"
				+ temp.getEndtime() + "</td>"+
				"<td>"+temp.getSemester()+" /"+temp.getYear() + "</td></tr>";
	}

	message += "</table>";
	message += "<div align=\"left\"><ul><li><b> <a href=\"InstructorFirstPage\">View Classes in Current Semesters</a></li><b>";
	message += "<li><b><a href=\"ViewGradeSheets?userid="+userid+"\">View GradeSheets</a><b></li></ul>";
	request.setAttribute("message", message);
	request.getServletContext().getRequestDispatcher("/i_output.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
