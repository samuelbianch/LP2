package ifmt.cba.vo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class FornecedorVO extends PessoaJuridicaVO {

    public FornecedorVO(String razaoSocial, String nomeFantasia, List<ProdutoVO> produtoVO) {
        super(razaoSocial, nomeFantasia);
        this.produtoVO = produtoVO;
    }

    @ManyToMany(mappedBy = "fornecedorVO", fetch = FetchType.EAGER)
    public List<ProdutoVO> produtoVO;

    public List<ProdutoVO> getProdutoVO() {
        return produtoVO;
    }

    public void setProdutoVO(List<ProdutoVO> produtoVO) {
        this.produtoVO = produtoVO;
    }

}
