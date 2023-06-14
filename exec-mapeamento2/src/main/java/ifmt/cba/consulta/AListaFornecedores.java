package ifmt.cba.consulta;

import java.util.List;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.FornecedorVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class AListaFornecedores {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery(
                    "SELECT b.codigo, b.nome, c.codigo, c.nome FROM FornecedorVO b JOIN ProdutoVO c ON b.codigo = c.codigo");

            List<FornecedorVO> lista = query.getResultList();

            System.out.println(lista.get(0).getCodigo());
            System.out.println(lista.get(0).getNome());

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERRO!:" + e.getMessage());
        }

    }
}
