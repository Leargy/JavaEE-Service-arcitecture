package com.example.fistservice.repository.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.annotation.ManagedBean;

@ManagedBean
public class HibernateUtils implements HibernateUtilsInterface{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
