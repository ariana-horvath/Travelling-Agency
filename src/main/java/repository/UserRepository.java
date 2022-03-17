package repository;

import model.User;
import model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class UserRepository {

    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("travel_agency");

    public void insertUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public User findUserByUsername (String username) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("User not found.");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public void addBookedVacation(User user, VacationPackage vacationPackage) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.merge(vacationPackage);
        em.getTransaction().commit();
        em.close();
    }
}
