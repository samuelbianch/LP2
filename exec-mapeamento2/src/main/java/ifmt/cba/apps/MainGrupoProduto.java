package ifmt.cba.apps;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.GrupoProdutoVO;
import jakarta.persistence.EntityManager;

public class MainGrupoProduto {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            GrupoProdutoVO a = new GrupoProdutoVO("Informatica");
            entityManager.persist(a);

            a = new GrupoProdutoVO("Eletrodomestico");
            entityManager.persist(a);

            a = new GrupoProdutoVO("Sofas");
            entityManager.persist(a);

            entityManager.getTransaction().commit();
            System.out.println("Inclusao realizada de Grupo de Produtos");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar Grupo de produtos: " + e.getMessage());
        }
    }
}
