package ifmt.cba.apps;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.A1;
import ifmt.cba.vo.B1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class AppA1i {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT b FROM B1 b WHERE b.nome = 'b1'");
            B1 b = (B1) query.getSingleResult();

            A1 a = new A1("a1");
            a.setB1(b);

            entityManager.persist(a);

            query = entityManager.createQuery("SELECT b FROM B1 b WHERE b.nome = 'b2'");
            b = (B1) query.getSingleResult();

            a = new A1("a2");
            a.setB1(b);

            entityManager.persist(a);
            entityManager.getTransaction().commit();
            System.out.println("Inclusao realizada");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
