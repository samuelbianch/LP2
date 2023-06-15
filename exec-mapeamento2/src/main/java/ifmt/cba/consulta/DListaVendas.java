package ifmt.cba.consulta;

import java.util.List;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.ClienteVO;
import ifmt.cba.vo.ItemVendaVO;
import ifmt.cba.vo.VendaVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class DListaVendas {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Query query2 = entityManager
                    .createQuery("SELECT b FROM ItemVendaVO b");
            List<ItemVendaVO> listaItemVenda = query2.getResultList();

            for (ItemVendaVO itemVenda : listaItemVenda) {
                for (VendaVO venda : itemVenda.getVenda()) {
                    System.out.println("----------------------------------");
                    System.out.println("Codigo da venda........: " + venda.getCodigo());
                    System.out.println("Data da venda..........: " + venda.getDataVenda());
                    System.out.println("Codigo do Item da venda: " + itemVenda.getCodigo());
                    System.out.println("Quantidade.............: " + itemVenda.getQuantidade());
                    System.out.println("Preco de venda.........: " + itemVenda.getPrecoVenda());
                    System.out.println("Percentual de desconto.: " + itemVenda.getPerDesconto());
                    System.out.println("----------------------------------\n");
                }
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ERRO!: " + e.getMessage());
        }
    }
}
