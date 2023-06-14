package ifmt.cba.apps;

import java.util.Calendar;
import java.util.List;

import ifmt.cba.persistencia.EntityManagerUtil;
import ifmt.cba.vo.ClienteVO;
import ifmt.cba.vo.FornecedorVO;
import ifmt.cba.vo.GrupoProdutoVO;
import ifmt.cba.vo.ItemVendaVO;
import ifmt.cba.vo.ProdutoVO;
import ifmt.cba.vo.VendaVO;
import ifmt.cba.vo.VendedorVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class Main {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();

            // Incluindo Grupo de Produtos
            GrupoProdutoVO a = new GrupoProdutoVO("Informatica");
            entityManager.persist(a);

            a = new GrupoProdutoVO("Eletrodomestico");
            entityManager.persist(a);

            a = new GrupoProdutoVO("Sofas");
            entityManager.persist(a);

            // Incluindo Produto
            Query query = entityManager.createQuery("SELECT b FROM GrupoProdutoVO b WHERE b.codigo = 1");
            GrupoProdutoVO grupoProduto = (GrupoProdutoVO) query.getSingleResult();
            ProdutoVO produto = new ProdutoVO("Teclado", 85, grupoProduto);
            entityManager.persist(produto);

            query = entityManager.createQuery("SELECT b FROM GrupoProdutoVO b WHERE b.codigo = 2");
            grupoProduto = (GrupoProdutoVO) query.getSingleResult();
            produto = new ProdutoVO("Televisao", 300, grupoProduto);
            entityManager.persist(produto);

            query = entityManager.createQuery("SELECT b FROM GrupoProdutoVO b WHERE b.codigo = 3");
            grupoProduto = (GrupoProdutoVO) query.getSingleResult();
            produto = new ProdutoVO("Sofa-cama", 700, grupoProduto);
            entityManager.persist(produto);

            // Incluindo Fornecedor
            query = entityManager.createQuery("SELECT b FROM ProdutoVO b WHERE b.codigo = 1 AND b.codigo = 2");
            List<ProdutoVO> listaproduto = query.getResultList();
            FornecedorVO fornecedor = new FornecedorVO("Casas Bahia LTDA", "Casas Bahia", listaproduto);
            entityManager.persist(fornecedor);

            query = entityManager.createQuery("SELECT b FROM ProdutoVO b WHERE b.codigo = 2");
            listaproduto = query.getResultList();
            fornecedor = new FornecedorVO("Lojas Americanas LTDA", "Lojas Americanas", listaproduto);
            entityManager.persist(fornecedor);

            query = entityManager.createQuery("SELECT b FROM ProdutoVO b WHERE b.codigo = 1 AND b.codigo = 3");
            listaproduto = query.getResultList();
            fornecedor = new FornecedorVO("Lojas Americanas LTDA", "Lojas Americanas", listaproduto);
            entityManager.persist(fornecedor);

            // Inclusao de fornecedor com produto
            query = entityManager.createQuery("SELECT b FROM FornecedorVO b");
            List<FornecedorVO> listaFornecedor = query.getResultList();
            query = entityManager.createQuery("SELECT b FROM ProdutoVO b");
            List<ProdutoVO> listaProduto = query.getResultList();

            FornecedorVO fornecedorTemp = listaFornecedor.get(0);
            fornecedorTemp.setProdutoVO(listaProduto.subList(0, 2));

            entityManager.persist(fornecedorTemp);

            // Incluindo cliente
            ClienteVO cliente = new ClienteVO("Samuel", "06105917112", 3000);
            entityManager.persist(cliente);

            cliente = new ClienteVO("Paulo", "06158753154", 9000);
            entityManager.persist(cliente);

            cliente = new ClienteVO("Ana", "45612378945", 5000);
            entityManager.persist(cliente);

            // Cadastrando vendedor
            VendedorVO vendedor = new VendedorVO("Fulano", "12345678987", 5);
            entityManager.persist(vendedor);

            vendedor = new VendedorVO("Ciclano", "55445522123", 8);
            entityManager.persist(vendedor);

            vendedor = new VendedorVO("Beltrano", "44771122336", 2);
            entityManager.persist(vendedor);

            // Cadastra uma venda
            query = entityManager.createQuery("SELECT b FROM VendedorVO b WHERE b.codigo = 7");
            vendedor = (VendedorVO) query.getSingleResult();
            VendaVO venda = new VendaVO(Calendar.getInstance().getTime(), vendedor);
            entityManager.persist(venda);

            query = entityManager.createQuery("SELECT b FROM VendedorVO b WHERE b.codigo = 8");
            vendedor = (VendedorVO) query.getSingleResult();
            venda = new VendaVO(Calendar.getInstance().getTime(), vendedor);
            entityManager.persist(venda);

            query = entityManager.createQuery("SELECT b FROM VendedorVO b WHERE b.codigo = 9");
            vendedor = (VendedorVO) query.getSingleResult();
            venda = new VendaVO(Calendar.getInstance().getTime(), vendedor);
            entityManager.persist(venda);

            query = entityManager.createQuery("SELECT b FROM VendedorVO b WHERE b.codigo = 7");
            vendedor = (VendedorVO) query.getSingleResult();
            venda = new VendaVO(Calendar.getInstance().getTime(), vendedor);
            entityManager.persist(venda);

            query = entityManager.createQuery("SELECT b FROM VendedorVO b WHERE b.codigo = 8");
            vendedor = (VendedorVO) query.getSingleResult();
            venda = new VendaVO(Calendar.getInstance().getTime(), vendedor);
            entityManager.persist(venda);

            // Cadastrando um item de venda
            query = entityManager.createQuery("SELECT b FROM ProdutoVO b WHERE b.codigo = 1");
            ProdutoVO produtoVO = (ProdutoVO) query.getSingleResult();
            query = entityManager.createQuery("SELECT b FROM VendaVO b WHERE b.codigo = 1");
            List<VendaVO> vendaVO = query.getResultList();
            ItemVendaVO itemVenda = new ItemVendaVO(1, 50, 10, vendaVO, produtoVO);
            entityManager.persist(itemVenda);

            query = entityManager.createQuery("SELECT b FROM ProdutoVO b WHERE b.codigo = 2");
            produtoVO = (ProdutoVO) query.getSingleResult();
            query = entityManager.createQuery("SELECT b FROM VendaVO b WHERE b.codigo = 2");
            vendaVO = query.getResultList();
            itemVenda = new ItemVendaVO(3, 500, 0, vendaVO, produtoVO);
            entityManager.persist(itemVenda);

            query = entityManager.createQuery("SELECT b FROM ProdutoVO b WHERE b.codigo = 3");
            produtoVO = (ProdutoVO) query.getSingleResult();
            query = entityManager.createQuery("SELECT b FROM VendaVO b WHERE b.codigo = 3");
            vendaVO = query.getResultList();
            itemVenda = new ItemVendaVO(9, 254, 15, vendaVO, produtoVO);
            entityManager.persist(itemVenda);

            entityManager.getTransaction().commit();
            System.out.println("Inclusao realizada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro!: " + e.getMessage());
        }
    }
}
