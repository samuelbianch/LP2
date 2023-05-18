package ifmt.cba.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class ProdutoVO {

    @Id
    @Column(nullable = false)
    private int codigo;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, name = "precovenda")
    private float precoVenda;

    @OneToMany(fetch = FetchType.EAGER)
    private GrupoProdutoVO codiGrupoProdutoVO;

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

    public GrupoProdutoVO getCodiGrupoProdutoVO() {
        return codiGrupoProdutoVO;
    }

    public void setCodiGrupoProdutoVO(GrupoProdutoVO codiGrupoProdutoVO) {
        this.codiGrupoProdutoVO = codiGrupoProdutoVO;
    }

}
