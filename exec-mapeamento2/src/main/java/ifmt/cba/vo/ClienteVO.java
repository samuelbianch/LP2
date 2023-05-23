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

    public ClienteVO(int id, float limiteCredito) {
        this.id = id;
        this.limiteCredito = limiteCredito;
    }

    public ClienteVO(String nome, String cpf, int id, float limiteCredito) {
        super(nome, cpf);
        this.id = id;
        this.limiteCredito = limiteCredito;
    }

    public ClienteVO(int codigo, String nome, String nome2, String cpf, int id, float limiteCredito) {
        super(codigo, nome, nome2, cpf);
        this.id = id;
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
