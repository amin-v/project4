package configController;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("project");

    public static EntityManager getEntitymanager() {
        return factory.createEntityManager();
    }
}
