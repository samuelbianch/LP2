package ifmt.cba.consulta;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.util.ComparatorPessoaNome;
import ifmt.cba.vo.ItemVendaVO;
import ifmt.cba.vo.PessoaVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class FListaPessoa {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            ComparatorPessoaNome organizador = new ComparatorPessoaNome();

            Query query2 = entityManager
                    .createQuery("SELECT b FROM PessoaVO b ORDER BY :pNome");
            query2.setParameter("pNome", "nome");
            List<PessoaVO> listaPessoaVO = query2.getResultList();
            listaPessoaVO.sort(organizador);

            for (PessoaVO pessoa : listaPessoaVO) {
                System.out.println("----------------------------------");
                System.out.println("Codigo........: " + pessoa.getCodigo());
                System.out.println("Nome..........: " + pessoa.getNome());
                System.out.println("----------------------------------\n");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("ERRO!: " + e.getMessage());
        }
    }
}
