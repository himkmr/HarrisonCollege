package Meenu;

import java.io.IOException;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hclass;
import model.Hclassenrollment;
import customTools.DBUtil;

/**
 * Servlet implementation class DropClass
 */
@WebServlet("/DropClass")
public class DropClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DropClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fullList = "";
		/*
		String currentYear = (String) session.getAttribute("currentYear");
		String currentSemester = (String) session.getAttribute("currentSemester");
		String departmentName = request.getParameter("departmentName");*/
		String alert = "";
	System.out.println("got to dropclass");
		//Use the department name
	/*	TypedQuery<Hclass> q = DBUtil.createQuery("SELECT h FROM Hclass h where h.hcours.hdepartment.name = ?1 and h.semester = ?2 and h.year = ?3",Hclass.class)
				.setParameter(1, departmentName).setParameter(2, currentSemester).setParameter(3, currentYear);*/
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	
}


