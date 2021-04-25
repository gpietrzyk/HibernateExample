package pl.github.gpietrzyk.DAO;

import org.hibernate.Session;
import pl.github.gpietrzyk.model.Team;
import pl.github.gpietrzyk.util.HibernateUtil;

import java.util.List;

public class TeamDAO {

    public Team getTeamById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Team team = session.get(Team.class, id);
        session.close();
        return team;
    }

    public List<Team> getAllTeams() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Team.findAll", Team.class)
                    .getResultList();
        }
    }

    public void deleteTeam(Team team) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(team);
        session.getTransaction().commit();
        session.close();
    }

    public void saveTeam(Team team) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(team);
        session.getTransaction().commit();
        session.close();
    }
}
