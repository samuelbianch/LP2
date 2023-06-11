package ifmt.cba.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class ClienteVO extends PessoaFisicaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "limitecredito")
    private float limiteCredito;

    public ClienteVO() {

    }

    public ClienteVO(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public ClienteVO(String nome, String cpf, float limiteCredito) {
        super(nome, cpf);
        this.limiteCredito = limiteCredito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

}
