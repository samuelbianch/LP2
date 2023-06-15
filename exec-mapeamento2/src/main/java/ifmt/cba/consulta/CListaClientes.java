package ifmt.cba.consulta;

import java.util.ArrayList;
import java.util.List;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.ClienteVO;
import ifmt.cba.vo.FornecedorVO;
import ifmt.cba.vo.ProdutoVO;
import ifmt.cba.vo.VendaVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class CListaClientes {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Query query1 = entityManager
                    .createQuery("SELECT b FROM ClienteVO b ORDER BY b ASC");
            List<ClienteVO> listaCliente = query1.getResultList();
            List<List<VendaVO>> venda = new ArrayList<>();
            int i = 0;
            for (ClienteVO cliente : listaCliente) {
                Query query2 = entityManager
                        .createQuery("SELECT b FROM VendaVO b WHERE b.clienteVO = :Cliente");
                query2.setParameter("Cliente", cliente);
                venda.add(i, query2.getResultList());
                i += 1;
            }
            i = 0;
            for (ClienteVO cliente : listaCliente) {
                System.out.println("----------------------------------");
                System.out.println("Codigo do cliente........: " + cliente.getCodigo());
                System.out.println("Nome do cliente..........: " + cliente.getNome());
                System.out.println("Quantidade de compras....: " + venda.get(i).size());
                System.out.println("----------------------------------");
                i += 1;
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ERRO!: " + e.getMessage());
        }
    }
}
