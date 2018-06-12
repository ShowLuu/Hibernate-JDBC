package com.jingluu.demo.hibernate;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jingluu.demo.hibernate.entity.User;
/**
 * 
 *
 */
public class HibernateSQLTest {
	private SessionFactory sessionFactory;
	
	/*@Before
    public void before(){
		//读取默认的hibernate.cfg.xml
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		//读取指定的配置文件
		//sessionFactory = new Configuration().configure("config/hibernate.xml").buildSessionFactory();
    }*/
	
	@Before
    public void before(){
		//读取默认的hibernate.cfg.xml
		StandardServiceRegistryBuilder builder =  new StandardServiceRegistryBuilder().configure();
		
		//构建StandardServiceRegistry
		StandardServiceRegistry registry = builder.build();
		MetadataSources metadataSources = new MetadataSources(registry);
		
		//通过StandardServiceRegistry构建Metadata
		Metadata metadata = metadataSources.buildMetadata();
		
		//通过Metadata构建SessionFactory
		sessionFactory = metadata.buildSessionFactory();
		 
    }
	 
	@Test
	@Ignore
	public void find1(){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			 
			/*String sql = "select id,username,password,created_time as createdTime from user where id = ?";
			Query query = session.createSQLQuery(sql);
			query.setString(0, "2");*/
			
			String sql = "select id,username,password,created_time as createdTime from user where id = :id";
			Query query = session.createSQLQuery(sql);
			query.setString("id", "2");
			
			query.setResultTransformer(Transformers.aliasToBean(User.class));
			List<User> users = query.list();
			
			System.out.println(users.size());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Test
	//@Ignore
	public void find2(){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			
			String sql = "select id,username,password,created_time as createdTime from user where id = :id";
			SQLQuery query = session.createSQLQuery(sql);
			query.setBigInteger("id", BigInteger.valueOf(2L));
			
			query.setResultTransformer(Transformers.aliasToBean(User.class));
			
			//1.设置基本数据类型
			//2.通过addScalar设置的字段才会有返回值
			query.addScalar("id",StandardBasicTypes.LONG);
			query.addScalar("username");
			query.addScalar("password");
			query.addScalar("createdTime");
			
			List<User> users = query.list();
			System.out.println(users);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	 
	@After
    public void after(){
		sessionFactory.close();
	}
}
