package ifmt.cba.apps;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.PessoaJuridicaVO;
import jakarta.persistence.EntityManager;

public class MainFornecedor {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            PessoaJuridicaVO juridica = new PessoaJuridicaVO("Casas Bahia LTDA", "Casas Bahia");
            entityManager.persist(juridica);

            juridica = new PessoaJuridicaVO("Lojas Americanas LTDA", "Lojas Americanas");
            entityManager.persist(juridica);

            juridica = new PessoaJuridicaVO("Lojas Americanas LTDA", "Lojas Americanas");
            entityManager.persist(juridica);

            entityManager.getTransaction().commit();
            System.out.println("Inclusao realizada de Pessoa Juridica");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar fornecedor: " + e.getMessage());
        }
    }
}
