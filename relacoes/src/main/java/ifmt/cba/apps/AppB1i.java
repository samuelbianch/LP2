package ifmt.cba.apps;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.B1;
import jakarta.persistence.EntityManager;

public class AppB1i {
    public static void main(String[] args) {
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();

            B1 b = new B1("b1");
            em.persist(b);

            b = new B1("b2");
            em.persist(b);

            b = new B1("b3");
            em.persist(b);

            em.getTransaction().commit();
            System.out.println("Inclusao realizada");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
