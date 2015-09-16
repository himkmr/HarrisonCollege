package himanshu;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import sun.util.calendar.BaseCalendar.Date;
import model.Hclass;
import model.Hclassenrollment;
import model.Hofficial;
import model.Hstudent;
import customTools.DBUtil;

public class ViewClasses {

	public static List <Hclass> getAllClasses(long userid)
	{

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Hofficial official = em.find(Hofficial.class, userid);
		if(official==null)
				return null;
		
		List <Hclass> list = official.getHclasses();
		if(list==null)
				return null;
		else
			return list;
	}
	
	//Returns all classes in current semester for the given Instructor
	public static List <Hclass> getAllClassesinCurrSem(long userid)
	{
		int month = Calendar.getInstance().get(Calendar.MONTH); 
		String sem=null;
		if(month >= 0 && month <= 4 )
			sem = "spring";
		else
			sem= "fall";
		
		System.out.println(sem);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Hofficial official = em.find(Hofficial.class, userid);
		if(official==null)
				return null;
		
		String q = "select t from Hclass t where t.hofficial=:official and t.semester=:sem";
		
		TypedQuery<Hclass> tq = DBUtil.createQuery(q, Hclass.class).setParameter("official", official).setParameter("sem", sem);
		
		List<Hclass> list = tq.getResultList();
		for(Hclass temp: list)
			System.out.println(temp.getClassId());
		
		return list;
	}
	
	//Returns all Classes in a semester for that Instructor
	public static List <Hclass> getAllClassesInSemester(long userid, String sem, String year)
	{
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Hofficial official = em.find(Hofficial.class, userid);
		if(official==null)
				return null;
		
		String q = "select t from Hclass t where t.hofficial=:official and t.semester=:sem and t.year=:year";
		
		TypedQuery<Hclass> tq = DBUtil.createQuery(q, Hclass.class).setParameter("official", official).setParameter("sem", sem).setParameter("year", year+"");
		
		List<Hclass> list = tq.getResultList();
		for(Hclass temp: list)
			System.out.println(temp.getClassId());
		
		return list;
	}
	
	
	// Return a list of students enrolled in a class
	public static List <Hstudent> getAllStudentsInClass(Hclass class_obj)
	{

		List<Hclassenrollment> all_enrollments=  class_obj.getHclassenrollments();
		List<Hstudent> active_enrollments= new ArrayList<Hstudent>();
		for(Hclassenrollment temp: all_enrollments)
		{
			if(temp.getEnrolled().equals("yes"))
				active_enrollments.add(temp.getHstudent());
		}
		return active_enrollments;
	}
	
	public static List <Hstudent> getAllStudentsAllClasses(long userid)
	{

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Hofficial official = em.find(Hofficial.class, userid);
		if(official==null)
				return null;
		
		List <Hclass> list = official.getHclasses();
		List <Hstudent> student_list =new ArrayList<Hstudent>();
		if(list==null)
				return null;
		else
		{
			for(Hclass temp : list)
			{
				student_list.addAll(ViewClasses.getAllStudentsInClass(temp));
			}
		}
		return student_list;
	}
	public static String getStudentsGrade(Hclass class_obj, Hstudent hstudent)
	{
			String q = "select t from Hclassenrollment t where t.hstudent=:hstudent and t.hclass=:hclass";		
			System.out.println(q);
			TypedQuery<Hclassenrollment> tq = DBUtil.createQuery(q, Hclassenrollment.class).setParameter("hclass", class_obj).setParameter("hstudent", hstudent);
			Hclassenrollment enrollment = tq.getSingleResult();
			if(enrollment==null)
				return null;
			else
				return 
					enrollment.getGrade();
	}

	
	
}
