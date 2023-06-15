package ifmt.cba.consulta;

import java.util.List;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.util.ComparatorPessoaNome;
import ifmt.cba.vo.PessoaFisicaVO;
import ifmt.cba.vo.PessoaVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class GListaPessoaFisica {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            ComparatorPessoaNome organizador = new ComparatorPessoaNome();

            Query query2 = entityManager
                    .createQuery("SELECT b FROM PessoaFisicaVO b ORDER BY :pNome");
            query2.setParameter("pNome", "nome");
            List<PessoaFisicaVO> listaPessoaFisicaVO = query2.getResultList();
            listaPessoaFisicaVO.sort(organizador);

            for (PessoaFisicaVO pessoa : listaPessoaFisicaVO) {
                System.out.println("----------------------------------");
                System.out.println("Codigo........: " + pessoa.getCodigo());
                System.out.println("Nome..........: " + pessoa.getNome());
                System.out.println("RG............: " + pessoa.getRg());
                System.out.println("CPF...........: " + pessoa.getCpf());
                System.out.println("----------------------------------\n");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ERRO!: " + e.getMessage());
        }
    }
}
