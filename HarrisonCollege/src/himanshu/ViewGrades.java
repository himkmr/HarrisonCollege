package himanshu;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Hclass;
import model.Hclassenrollment;
import customTools.DBUtil;


public class ViewGrades {

	public static List<Hclassenrollment> getClassGrades(long classid, String semester, String year)
	{

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Hclass class_obj = em.find(Hclass.class,classid);

		String q = "select t from Hclassenrollment t where t.hclass=:class_o and t.hclass.semester=:sem and t.hclass.year=:year";	
		TypedQuery<Hclassenrollment> tq = DBUtil.createQuery(q, Hclassenrollment.class).setParameter("sem", semester).setParameter("class_o", class_obj).setParameter("year",year);
	
		List<Hclassenrollment> list = tq.getResultList();
		for(Hclassenrollment temp: list)
			System.out.println(temp.getGrade());
		
		return list;
	}
	
	public static List<Hclassenrollment> getClassGrades(long classid)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Hclass class_obj = em.find(Hclass.class,classid);

		String q = "select t from Hclassenrollment t where t.hclass=:class_o";	
		TypedQuery<Hclassenrollment> tq = DBUtil.createQuery(q, Hclassenrollment.class).setParameter("class_o", class_obj);
	
		List<Hclassenrollment> list = tq.getResultList();
		for(Hclassenrollment temp: list)
			System.out.println(temp.getGrade());
		
		return list;
	}
	
	
	
}
