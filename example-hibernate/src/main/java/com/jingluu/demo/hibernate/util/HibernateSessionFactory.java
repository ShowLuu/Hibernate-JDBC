package com.jingluu.demo.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactory {
     private static SessionFactory sessionFactory;
     private static final String HIBERNATE_CFG = "hibernate.cfg.xml";
     private static ThreadLocal<Session> session = new ThreadLocal<Session>();
     
     static {
    	 buildSessionFactory();
     }
     
     public static void buildSessionFactory(){
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
     
     /**
      * 线程安全Session（方式一）
      * 需要在hibernate.cfg.xml配置：
      * <property name="hibernate.current_session_context_class">thread</property>
      * @return
      */
     public static Session getCurrentSession(){
    	 return sessionFactory.getCurrentSession();
     }
     
     /**
      * 线程安全Session（方式二）
      * @return
      */
     public static Session openSession(){
    	 Session currentSession = session.get();
    	 if(currentSession == null){
    		 currentSession = sessionFactory.openSession();
    		 session.set(currentSession);
    	 }
    	 return currentSession;
     }
     
     public static void close(){
 		sessionFactory.close();
 	}
     
}
