package ifmt.cba.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "itemvenda")
public class ItemVendaVO {

    @Id
    @Column(nullable = false)
    private int codigo;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private int precoVenda;

    @Column(nullable = false, name = "perdesconto")
    private float perDesconto;

    @OneToMany
    private VendaVO venda;

    @OneToMany
    private ProdutoVO produto;

    public ItemVendaVO() {
    }

    public ItemVendaVO(int codigo, int quantidade, int precoVenda, float perDesconto, VendaVO venda,
            ProdutoVO produto) {
        this.codigo = codigo;
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

    public VendaVO getVenda() {
        return venda;
    }

    public void setVenda(VendaVO venda) {
        this.venda = venda;
    }

    public ProdutoVO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }
}
