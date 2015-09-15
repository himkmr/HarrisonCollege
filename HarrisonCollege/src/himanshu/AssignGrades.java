package himanshu;

import java.util.List;
import javax.persistence.EntityManager;
import model.Hclassenrollment;
import model.Hstudent;
import customTools.DBUtil;

public class AssignGrades {
	public static void assignGrades(int userid, int classid, String grades)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Hstudent student = em.find(Hstudent.class, userid);
		List <Hclassenrollment> enrollments = student.getHclassenrollments();
		for(Hclassenrollment temp: enrollments )
		{
			if(temp.getHclass().getClassId() == classid)
			{
					temp.setGrade(grades);
					return;
			}
		}
	}
}
