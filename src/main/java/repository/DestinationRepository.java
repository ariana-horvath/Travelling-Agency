package repository;

import model.Destination;
import model.VacationPackage;

import javax.persistence.*;
import java.util.List;

public class DestinationRepository {

    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("travel_agency");
    private final VacationPackageRepository vacationPackageRepository;

    public DestinationRepository() {
        vacationPackageRepository = new VacationPackageRepository();
    }

    public void insertDestination(Destination destination) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(destination);
        em.getTransaction().commit();
        em.close();
    }

    public Destination findDestinationByName (String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("SELECT d FROM Destination d WHERE d.name = :name", Destination.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No destination found.");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public void deleteDestinationByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Destination dest = em.createQuery("SELECT d FROM Destination d WHERE d.name = :name", Destination.class)
                .setParameter("name", name).getSingleResult();
        em.getTransaction().commit();
        em.close();

        for(VacationPackage vacationPackage : vacationPackageRepository.findAll()) {
            if(vacationPackage.getDestination().getName().compareTo(dest.getName()) == 0) {
                vacationPackageRepository.deletePackageByName(vacationPackage.getName());
            }
        }

        EntityManager em2 = entityManagerFactory.createEntityManager();
        em2.getTransaction().begin();
            Destination dest2 = em2.createQuery("SELECT d FROM Destination d WHERE d.name = :name", Destination.class)
                    .setParameter("name", name).getSingleResult();
            em2.remove(dest2);
            em2.getTransaction().commit();
            em2.close();
    }

    public List<Destination> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("SELECT d FROM Destination d", Destination.class).getResultList();
        } catch (NoResultException e) {
            System.out.println("Destinations not found.");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }
}
