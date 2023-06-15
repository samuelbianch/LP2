package ifmt.cba.consulta;

import java.util.List;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.FornecedorVO;
import ifmt.cba.vo.GrupoProdutoVO;
import ifmt.cba.vo.ProdutoVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class BListaProdutos {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Query query1 = entityManager
                    .createQuery("SELECT b FROM ProdutoVO b ORDER BY b ASC");
            List<ProdutoVO> listaProduto = query1.getResultList();

            for (ProdutoVO produto : listaProduto) {
                System.out.println("----------------------------------");
                System.out.println("Codigo do produto........: " + produto.getCodigo());
                System.out.println("Nome do produto..........: " + produto.getNome());
                System.out.println("Preco de venda...........: " + produto.getPrecoVenda());
                System.out.println("Nome do grupo de produto.: " + produto.getGrupoProdutoVO().getNome());
                for (FornecedorVO fornecedor : produto.getPessoaJuridicaVO()) {
                    System.out.println("Codigo do fornecedor.....: " + fornecedor.getCodigo());
                    System.out.println("Nome do fornecedor.......: " + fornecedor.getRazaoSocial());
                    System.out.println("----------------------------------\n");
                }
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ERRO!: " + e.getMessage());
        }
    }
}
