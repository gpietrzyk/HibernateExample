package pl.github.gpietrzyk.DAO;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.github.gpietrzyk.model.User;
import pl.github.gpietrzyk.util.HibernateUtil;

import java.util.List;


public class UserDAO {

    public User getUserById(Integer id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public void deleteEmployee(Integer id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    public void updateUser(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void saveUser(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<User> getAllUsers(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createNamedQuery("User.findAll", User.class)
                    .getResultList();
        }
    }

    public void deleteAllUsers(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createNamedQuery("User.deleteAll");
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
