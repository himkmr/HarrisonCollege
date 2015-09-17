package himanshu;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Hclassenrollment;
import customTools.DBUtil;

public class Update {

	//gets the user and class objects
	//then gets the enrollment for that student and class
	//set the new grades and update
	public static void UpdateStudentGrade(String userid, String classid, String grades) {
	
		System.out.println("user "+ userid);
		System.out.println("class "+classid);
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		model.Hclass class_obj = em.find(model.Hclass.class, Long.parseLong(classid));
		model.Hstudent student_obj = em.find(model.Hstudent.class, Long.parseLong(userid));
		
		if(class_obj==null)
			System.out.println("class_obj is null");
		if(student_obj==null)
			System.out.println("student_obj is null");
		
		String q = "select t from Hclassenrollment t where t.hclass=:hclass and t.hstudent=:student";
		TypedQuery<Hclassenrollment> tq = DBUtil.createQuery(q, Hclassenrollment.class).setParameter("hclass", class_obj).setParameter("student", student_obj);
		Hclassenrollment enrollment =tq.getSingleResult();
		
		enrollment.setGrade(grades);
		DBUtil.updateDB(enrollment);
		
	}

}
