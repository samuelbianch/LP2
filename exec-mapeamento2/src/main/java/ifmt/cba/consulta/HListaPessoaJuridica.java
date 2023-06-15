package ifmt.cba.consulta;

import java.util.List;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.util.ComparatorPessoaNome;
import ifmt.cba.vo.PessoaJuridicaVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class HListaPessoaJuridica {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            ComparatorPessoaNome organizador = new ComparatorPessoaNome();

            Query query2 = entityManager
                    .createQuery("SELECT b FROM PessoaJuridicaVO b ORDER BY :pNome");
            query2.setParameter("pNome", "nome");
            List<PessoaJuridicaVO> listaPessoaJuridicaVO = query2.getResultList();
            listaPessoaJuridicaVO.sort(organizador);

            for (PessoaJuridicaVO pessoa : listaPessoaJuridicaVO) {
                System.out.println("----------------------------------");
                System.out.println("Codigo........: " + pessoa.getCodigo());
                System.out.println("Nome Fantasia.: " + pessoa.getNomeFantasia());
                System.out.println("CNPJ..........: " + pessoa.getCnpj());
                System.out.println("----------------------------------\n");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("ERRO!: " + e.getMessage());
        }
    }
}
