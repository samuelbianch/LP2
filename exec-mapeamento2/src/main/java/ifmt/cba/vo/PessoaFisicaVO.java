package ifmt.cba.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoafisica")
public class PessoaFisicaVO extends PessoaVO {

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, length = 9)
    private String rg;

    public PessoaFisicaVO() {
    }

    public PessoaFisicaVO(String nome, String cpf, String rg) {
        super(nome);
        this.cpf = cpf;
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

}
