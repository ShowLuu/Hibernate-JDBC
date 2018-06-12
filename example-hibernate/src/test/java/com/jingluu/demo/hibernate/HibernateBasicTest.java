package com.jingluu.demo.hibernate;

import java.util.Date;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jingluu.demo.hibernate.entity.User;

public class HibernateBasicTest {
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
    public void testUser(){
		//打开新的Session
		Session session = sessionFactory.openSession();
		
		//实体
		User user = new User();
		user.setId(System.currentTimeMillis());
		user.setUsername("张三");
		user.setPassword("123456");
		user.setEnabled(1);
		user.setCreatedTime(new Date());
		
		//开启事务
		session.beginTransaction();
		
		//执行数据库操作
		session.save(user);
		
		//提交事务
		session.getTransaction().commit();
		
		//关闭session
		session.close();
    }
	
	@Test
	public void testRole(){
		 
	}
	
	@After
    public void after(){
		sessionFactory.close();
	}
}
