package ifmt.cba.consulta;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.FornecedorVO;
import ifmt.cba.vo.ProdutoVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class AListaFornecedores {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Query query1 = entityManager
                    .createQuery("SELECT b FROM FornecedorVO b ORDER BY b ASC");
            List<FornecedorVO> listaFornecedor = query1.getResultList();

            for (FornecedorVO fornecedor : listaFornecedor) {
                for (ProdutoVO produto : fornecedor.getProdutoVO()) {
                    System.out.println("----------------------------------");
                    System.out.println("Codigo do fornecedor........: " + fornecedor.getCodigo());
                    System.out.println("Nome do fornecedor..........: " + fornecedor.getNome());
                    System.out.println("Codigo do produto...........: " + produto.getCodigo());
                    System.out.println("Nome do produto.............: " + produto.getNome());
                    System.out.println("----------------------------------\n");
                }
            }

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERRO!:" + e.getMessage());
        }

    }
}
