package ifmt.cba.vo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "itemvenda")
public class ItemVendaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private int precoVenda;

    @Column(nullable = false, name = "perdesconto")
    private float perDesconto;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<VendaVO> venda;

    @ManyToOne
    private ProdutoVO produto;

    public ItemVendaVO() {
    }

    public ItemVendaVO(int quantidade, int precoVenda, float perDesconto, List<VendaVO> venda,
            ProdutoVO produto) {
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.perDesconto = perDesconto;
        this.venda = venda;
        this.produto = produto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(int precoVenda) {
        this.precoVenda = precoVenda;
    }

    public float getPerDesconto() {
        return perDesconto;
    }

    public void setPerDesconto(float perDesconto) {
        this.perDesconto = perDesconto;
    }

    public List<VendaVO> getVenda() {
        return venda;
    }

    public void setVenda(List<VendaVO> venda) {
        this.venda = venda;
    }

    public ProdutoVO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }
}
