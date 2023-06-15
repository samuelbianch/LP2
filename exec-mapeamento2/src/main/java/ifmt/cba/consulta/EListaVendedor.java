package ifmt.cba.consulta;

import java.util.List;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.ItemVendaVO;
import ifmt.cba.vo.VendaVO;
import ifmt.cba.vo.VendedorVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class EListaVendedor {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Query query2 = entityManager
                    .createQuery("SELECT b FROM VendedorVO b");
            List<VendedorVO> listaVendedor = query2.getResultList();
            double totalVenda = 0;

            for (VendedorVO vendedor : listaVendedor) {
                System.out.println("----------------------------------");
                System.out.println("Codigo do vendedor........: " + vendedor.getCodigo());
                System.out.println("Nome do vendedor..........: " + vendedor.getNome());
                query2 = entityManager.createQuery("SELECT b FROM VendaVO b WHERE b.vendedor.codigo = :vendedor");
                query2.setParameter("vendedor", vendedor.getCodigo());
                List<VendaVO> venda = query2.getResultList();
                int i = 0;
                while (i < venda.size()) {
                    System.out.println("Codigo da venda.......: " + venda.get(i).getCodigo());
                    query2 = entityManager.createQuery("SELECT b FROM ItemVendaVO b WHERE b.venda = :nCodigo");
                    query2.setParameter("nCodigo", venda.get(i));

                    if (query2 != null) {
                        List<ItemVendaVO> listaItemVenda = query2.getResultList();

                        for (ItemVendaVO itemVenda : listaItemVenda) {
                            totalVenda += itemVenda.getPrecoVenda();
                        }
                        i += 1;
                    }
                }
                System.out.println("Total da venda............: " + totalVenda);
                System.out.println("Valor comissao............:" + totalVenda * (vendedor.getPerComissao() / 100));
                System.out.println("----------------------------------\n");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ERRO!: " + e.getMessage());
        }
    }
}
