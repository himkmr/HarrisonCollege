package himanshu;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hofficial;
import customTools.DBUtil;

/**
 * Servlet implementation class GetStudentsInClass
 */
@WebServlet("/GetStudentsInClass")
public class GetStudentsInClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudentsInClass() {
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
	long classid = Long.parseLong(request.getParameter("classid"));
	EntityManager em = DBUtil.getEmFactory().createEntityManager();
	model.Hclass class_obj = em.find(model.Hclass.class, classid);
	List<model.Hstudent> students_in_class = himanshu.ViewClasses.getAllStudentsInClass(class_obj);

	String message = "";
	message += "<div class=\"container\" align=\"center\"><table class=\"table table-hover\"  align=\"center\"><tr bgcolor=\"#e6e6e6\"><td><b>Student Name</td><td><b> Student ID</td><td><b>Grades</td></tr>";
	for (model.Hstudent temp : students_in_class) {
		message += "<td>" + temp.getHuser().getName() + "</td><td>"
				+ temp.getStudentId()+ "</td><td><form action=\"UpdateGrade\"><input type=\"text\" name=\"grade\" value=\""+himanshu.ViewClasses.getStudentsGrade(class_obj, temp.getHuser().getHstudent())+"\">"
						+ "<input type=\"submit\" value=\"Update\">"
						+ "<input type=\"hidden\" name =\"studentid\" value=\""+temp.getStudentId()+"\">"
						+ "<input type=\"hidden\" name =\"classid\" value=\""+classid+"\">"
						+ "</form></td></tr>";
	}

	message += "</table>";
	
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
