package himanshu;
import java.util.List;
import javax.persistence.TypedQuery;
import model.Huser;
import customTools.DBUtil;


public class Authentication {

	//returns Huser object on success else null
	public static Huser authenticate_user(String userid, String password)
	{
		System.out.println("Authenticating");
		String q = "select t from Huser t where t.userId="+userid;
		TypedQuery<Huser> tq = DBUtil.createQuery(q, Huser.class);
		 List<Huser> mlist=tq.getResultList();
		 for(Huser temp: mlist)
		 {

			 String real_password = temp.getPassword();
			 System.out.println("real_password");
			 if(real_password.equals(password))
				 return temp;
		 }
		return null;	
	}
}
