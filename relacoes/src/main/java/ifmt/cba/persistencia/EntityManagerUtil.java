package ifmt.cba.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("relacoes");
    }

    private EntityManagerUtil() {

    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            return null;
        } else {
            return emf.createEntityManager();
        }
    }
}
