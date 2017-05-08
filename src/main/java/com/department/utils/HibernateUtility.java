package com.department.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created on 25.04.17.
 */
public class HibernateUtility {
    private static final Logger LOGGER = Logger.getLogger(HibernateUtility.class);
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            return sessionFactory;
        } catch (Throwable e) {
            System.err.println("SessionFactory initialization filed" + e);
            LOGGER.error("Error while getting unique user e-mail", e);
            throw new ExceptionInInitializerError(e);

        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
