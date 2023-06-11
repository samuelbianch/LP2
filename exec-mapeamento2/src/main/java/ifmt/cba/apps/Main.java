package ifmt.cba.apps;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.ClienteVO;
import ifmt.cba.vo.GrupoProdutoVO;
import ifmt.cba.vo.PessoaJuridicaVO;
import ifmt.cba.vo.ProdutoVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class Main {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            // Incluindo Grupo de Produtos
            GrupoProdutoVO a = new GrupoProdutoVO("Informatica");
            entityManager.persist(a);

            a = new GrupoProdutoVO("Eletrodomestico");
            entityManager.persist(a);

            a = new GrupoProdutoVO("Sofas");
            entityManager.persist(a);

            // Incluindo Produto
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

            // Incluindo pessoa Juridica (Fornecedor)
            PessoaJuridicaVO juridica = new PessoaJuridicaVO("Casas Bahia LTDA", "Casas Bahia");
            entityManager.persist(juridica);

            juridica = new PessoaJuridicaVO("Lojas Americanas LTDA", "Lojas Americanas");
            entityManager.persist(juridica);

            juridica = new PessoaJuridicaVO("Lojas Americanas LTDA", "Lojas Americanas");
            entityManager.persist(juridica);
            // System.out.println("Inclusao realizada de Pessoa Juridica");

            // Incluindo cliente
            ClienteVO cliente = new ClienteVO("Samuel", "06105917112", 3000);
            entityManager.persist(cliente);

            cliente = new ClienteVO("Paulo", "06158753154", 9000);
            entityManager.persist(cliente);

            cliente = new ClienteVO("Ana", "45612378945", 5000);
            entityManager.persist(cliente);

            entityManager.getTransaction().commit();
            System.out.println("Inclusao realizada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro!: " + e.getMessage());
        }
    }
}
