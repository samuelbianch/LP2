package ifmt.cba.vo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class FornecedorVO extends PessoaJuridicaVO {

    public FornecedorVO() {
        super(null, null, null);
    }

    public FornecedorVO(String razaoSocial, String nomeFantasia, String cnpj, List<ProdutoVO> produtoVO) {
        super(razaoSocial, nomeFantasia, cnpj);
        this.produtoVO = produtoVO;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<ProdutoVO> produtoVO;

    public List<ProdutoVO> getProdutoVO() {
        return produtoVO;
    }

    public void setProdutoVO(List<ProdutoVO> produtoVO) {
        this.produtoVO = produtoVO;
    }

}
