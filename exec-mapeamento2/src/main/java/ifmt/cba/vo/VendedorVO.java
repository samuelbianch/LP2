package ifmt.cba.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendedor")
public class VendedorVO extends PessoaFisicaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "percomissao")
    private float perComissao;

    public VendedorVO() {
    }

    public VendedorVO(float perComissao) {
        this.perComissao = perComissao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPerComissao() {
        return perComissao;
    }

    public void setPerComissao(float perComissao) {
        this.perComissao = perComissao;
    }
}
