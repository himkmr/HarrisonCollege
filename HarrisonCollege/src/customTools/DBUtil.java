package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DBUtil {
	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("HarrisonCollege");

	public static EntityManagerFactory getEmFactory() {
		return emf;
	}

	public static <T> void addToDB(Object T) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(T);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}

	}

	public static <T> TypedQuery<T> createQuery(String q, Class<T> className) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<T> tQuery = em.createQuery(q, className);
		return tQuery;
	}
	
	public static <T> T find(long pk, Class<T> className){
		EntityManager em = emf.createEntityManager();
		return em.find(className, pk);
	}

	public static <T> void updateDB(Object T) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(T);
			trans.commit();
			System.out.println("UPDATED!");
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
}