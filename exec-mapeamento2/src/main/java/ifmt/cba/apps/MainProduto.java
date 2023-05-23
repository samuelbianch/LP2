package ifmt.cba.apps;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.GrupoProdutoVO;
import ifmt.cba.vo.ProdutoVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class MainProduto {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT b FROM GrupoProdutoVO b WHERE b.codigo = 1");
            GrupoProdutoVO grupoProduto = (GrupoProdutoVO) query.getSingleResult();
            ProdutoVO produto = new ProdutoVO("Teclado", 85, grupoProduto);
            entityManager.persist(produto);

            query = entityManager.createQuery("SELECT b FROM GrupoProdutoVO b WHERE b.codigo = 2");
            grupoProduto = (GrupoProdutoVO) query.getSingleResult();
            produto = new ProdutoVO("Televisao", 300, grupoProduto);
            entityManager.persist(produto);

            query = entityManager.createQuery("SELECT b FROM GrupoProdutoVO b WHERE b.codigo = 3");
            grupoProduto = (GrupoProdutoVO) query.getSingleResult();
            produto = new ProdutoVO("Sofa-cama", 700, grupoProduto);
            entityManager.persist(produto);

            entityManager.getTransaction().commit();
            System.out.println("Inclusao realizada de Produtos");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar produtos: " + e.getMessage());
        }
    }
}
