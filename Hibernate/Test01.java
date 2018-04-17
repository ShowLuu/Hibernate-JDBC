package hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ss.hibernate.entity.User;

public class Test01 {
	
	private SessionFactory sessionFactory;
	
	public Test01(){
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public void test01(){
		Session session=sessionFactory.openSession();
//		User user=new User();
//		//user.setId(1L);
////		user.setUsername("123");
////		user.setPassword("121");
//		user.setEnable(1);
//		Transaction transaction=session.beginTransaction();
//		transaction.begin();
//		session.saveOrUpdate(user);
//		//session.save(user);
//		transaction.commit();
		String hql="update User set username=:username where Id=:id ";
		hql="from User where Id=?";
		Query query=session.createQuery(hql);
		//query.setParameter("username", "11");
		query.setParameter(0, 1L);
//		Transaction transaction=session.getTransaction();
//		transaction.begin();
//		query.executeUpdate();
//		transaction.commit();
		System.out.println(query.list().toString());
//		session.update(user);
	}
	
	public static void main(String[] args) {
		new Test01().test01();
	}

}
