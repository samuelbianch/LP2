package ifmt.cba.vo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class ProdutoVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, name = "precovenda")
    private float precoVenda;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grupoproduto")
    private GrupoProdutoVO grupoProdutoVO;

    @ManyToMany
    @JoinTable(name = "fornecedor", joinColumns = { @JoinColumn(name = "produto_id") }, inverseJoinColumns = {
            @JoinColumn(name = "fornecedor_id") })
    private List<FornecedorVO> fornecedorVO;

    public List<FornecedorVO> getPessoaJuridicaVO() {
        return fornecedorVO;
    }

    public void setPessoaJuridicaVO(List<FornecedorVO> fornecedorVO) {
        this.fornecedorVO = fornecedorVO;
    }

    public ProdutoVO() {
    }

    public ProdutoVO(String nome, float precoVenda, GrupoProdutoVO grupoProdutoVO) {
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.grupoProdutoVO = grupoProdutoVO;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public GrupoProdutoVO getGrupoProdutoVO() {
        return grupoProdutoVO;
    }

    public void setGrupoProdutoVO(GrupoProdutoVO grupoProdutoVO) {
        this.grupoProdutoVO = grupoProdutoVO;
    }
}
