package pl.github.gpietrzyk.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

    static {
        try{
            sessionFactory = configuration.buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() { return sessionFactory ;};

}
