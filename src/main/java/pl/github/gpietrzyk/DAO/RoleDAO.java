package pl.github.gpietrzyk.DAO;

import org.hibernate.Session;
import pl.github.gpietrzyk.model.Role;
import pl.github.gpietrzyk.util.HibernateUtil;

import java.util.List;

public class RoleDAO {

    public Role getRoleById(Integer id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Role role = session.get(Role.class, id);
        session.close();
        return role;
    }

    public void deleteRole(Role role) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(role);
        session.getTransaction().commit();
        session.close();
    }

    public List<Role> getAllRoles(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createNamedQuery("Role.findAll", Role.class)
                    .getResultList();
        }
    }

    public void saveRole(Role role){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(role);
        session.getTransaction().commit();
        session.close();
    }
}
