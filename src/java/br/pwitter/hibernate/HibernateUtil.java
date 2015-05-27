package br.pwitter.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static final SessionFactory session = buildSession();

    private static SessionFactory buildSession() {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            return cfg.buildSessionFactory();
            
            
            
        } catch (HibernateException b) {
            System.out.println("Entrou no Catch HibernateUtil: " + b.toString());
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getSessionFactory() {
        return session;
    }
}
