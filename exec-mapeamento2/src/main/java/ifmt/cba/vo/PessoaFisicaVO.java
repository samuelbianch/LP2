package ifmt.cba.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoafisica")
public class PessoaFisicaVO extends PessoaVO {

    @Column(nullable = false, length = 11)
    private String cpf;

    public PessoaFisicaVO() {
    }

    public PessoaFisicaVO(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
