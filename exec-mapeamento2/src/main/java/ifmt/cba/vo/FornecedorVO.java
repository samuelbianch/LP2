package ifmt.cba.vo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class FornecedorVO extends PessoaJuridicaVO {

    public FornecedorVO(int codigo, String nome, String razaoSocial, String nomeFantasia) {
        super(codigo, nome, razaoSocial, nomeFantasia);
    }

    @ManyToMany(targetEntity = ifmt.cba.vo.ProdutoVO.class)
    public List<ProdutoVO> produtoVO;

}
