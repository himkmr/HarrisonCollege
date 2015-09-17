package himanshu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hclass;
import model.Hclassenrollment;
import model.Hcours;
import model.Hdepartment;
import model.Htuitionfee;
import customTools.DBUtil;

/**
 * Servlet implementation class CalculateRevnue
 */
@WebServlet("/CalculateRevenue")
public class CalculateRevenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculateRevenue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String semester = request.getParameter("semesters");
			String year = request.getParameter("years");
			
			System.out.println(semester);
			System.out.println(year);
			String q2 = "select t from Htuitionfee t where t.semester=:semester and t.year=:year";	
			System.out.println(q2);
			TypedQuery<Htuitionfee> tq2 = DBUtil.createQuery(q2, Htuitionfee.class).setParameter("semester", semester).setParameter("year", year);
			Htuitionfee fee= tq2.getSingleResult();
			String fee_String = fee.getPerCreditTuition();
			System.out.println(fee_String);
			double creditpersem = Double.parseDouble(fee_String);
			
			List<Hcours> allcourses = getAllCourses(request.getParameter("departments"));

			int total_credit_hours=0;
			for(Hcours temp:allcourses)
			{
				int credit_hours = temp.getCreditHours();
				List<Hclass> classes = temp.getHclasses();	
				int student_count = 0;
				for(Hclass temp2:classes)
				{
					List<Hclassenrollment> enrolls = temp2.getHclassenrollments();
					student_count += enrolls.size();
				}
				total_credit_hours += total_credit_hours+(student_count  * credit_hours);
			
			}
			double revenue  = total_credit_hours * creditpersem;
			System.out.println(revenue);
			String message = "<div align=\"center\"><b>Revenue for "+semester+", "+year+" is : "+ revenue;
			request.setAttribute("message", message);
			request.getServletContext().getRequestDispatcher("/i_output.jsp").forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	public static List<model.Hcours> getAllCourses(String department) {
		String q = "select h from Hdepartment h where h.name ='"+department+"'";
		Hdepartment department_obj = DBUtil.createQuery(q, Hdepartment.class).getSingleResult();
		List<Hcours> courses = department_obj.getHcourses();
		return courses;
	}
}
