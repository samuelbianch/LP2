package ifmt.cba.vo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendedor")
public class VendedorVO extends PessoaFisicaVO {

    @Column(name = "percomissao")
    private float perComissao;

    public VendedorVO() {
    }

    public VendedorVO(String nome, String cpf, String rg, float perComissao) {
        super(nome, cpf, rg);
        this.perComissao = perComissao;
    }

    public float getPerComissao() {
        return perComissao;
    }

    public void setPerComissao(float perComissao) {
        this.perComissao = perComissao;
    }
}
