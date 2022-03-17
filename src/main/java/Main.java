import controller.MainController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("travel_agency");

    public static void main(String[] args) {
        new MainController();
    }
}

