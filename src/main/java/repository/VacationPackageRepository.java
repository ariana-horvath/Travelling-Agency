package repository;

import model.Destination;
import model.User;
import model.VacationPackage;

import javax.persistence.*;
import java.util.List;

public class VacationPackageRepository {
    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("travel_agency");

    public void insertVacationPackage(VacationPackage vacationPackage) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacationPackage);
        em.getTransaction().commit();
        em.close();
    }

    public VacationPackage findPackageByName (String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("SELECT p FROM VacationPackage p WHERE p.name = :name", VacationPackage.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Vacation Package not found.");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public List<VacationPackage> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("SELECT vp FROM VacationPackage vp", VacationPackage.class).getResultList();
        } catch (NoResultException e) {
            System.out.println("Vacation Packages not found.");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public void deletePackageByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM VacationPackage vp WHERE vp.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updatePackage(String name, VacationPackage vacationPackage) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE VacationPackage vp SET vp.name = :name, vp.details = :details, vp.destination =: " +
                "destination, vp.price = :price, vp.nbOfPlaces = :nbPlaces, vp.startDate =: startDate, vp.endDate = :endDate, " +
                "vp.status = :status, vp.availablePlaces = :availablePlaces  WHERE vp.name = :name2");
        query.setParameter("name", vacationPackage.getName());
        query.setParameter("details", vacationPackage.getDetails());
        query.setParameter("destination", vacationPackage.getDestination());
        query.setParameter("price", vacationPackage.getPrice());
        query.setParameter("nbPlaces", vacationPackage.getNbOfPlaces());
        query.setParameter("startDate", vacationPackage.getStartDate());
        query.setParameter("endDate", vacationPackage.getEndDate());
        query.setParameter("status", vacationPackage.getStatus());
        query.setParameter("availablePlaces", vacationPackage.getAvailablePlaces());
        query.setParameter("name2", name);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
