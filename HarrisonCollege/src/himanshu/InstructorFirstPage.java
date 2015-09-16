package himanshu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class InstructorForstPage
 */
@WebServlet("/InstructorFirstPage")
public class InstructorFirstPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstructorFirstPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		model.Huser user =  (model.Huser) request.getSession().getAttribute("User");
		long userid = user.getUserId();
		List <model.Hclass> classes_in_cur_sem = himanshu.ViewClasses.getAllClassesinCurrSem(userid);
		
		String message = "";
		message+="<table class=\"table table-hover\" style=\"width:60%\"><tr bgcolor=\"#C0C0C0\"><td><b>Course Name</td><td><b>Class Id </td><td><b>Days</td><td><b>Start time</td><td><b>End time</td></tr>";
		for(model.Hclass temp : classes_in_cur_sem)
		{
			message+="<td>"+temp.getHcours().getSubject()+"</td><td>"+
					temp.getClassId()+"</td><td>"+
					temp.getDay()+"</td><td>"+
					temp.getStarttime()+"</td><td>"+
					temp.getEndtime()+"</td><td>";
		}
		
		message+="</table>";
		request.setAttribute("message", message);

		 request.getServletContext().getRequestDispatcher("/i_output.jsp").forward(request, response);
	}

}
