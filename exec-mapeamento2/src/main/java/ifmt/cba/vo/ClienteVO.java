package ifmt.cba.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class ClienteVO extends PessoaFisicaVO {

    @Column(name = "limitecredito")
    private float limiteCredito;

    public ClienteVO() {

    }

    public ClienteVO(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public ClienteVO(String nome, String cpf, String rg, float limiteCredito) {
        super(nome, cpf, rg);
        this.limiteCredito = limiteCredito;
    }

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

}
