package com.jbk.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jbk.entity.Product;

public class HibernateConfiguration {
	

	public static SessionFactory getSessionFactory() {
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml").addAnnotatedClass(Product.class);
		
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		return sessionFactory;
		
	}
}
