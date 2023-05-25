package ifmt.cba.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoafisica")
public class PessoaFisicaVO extends PessoaVO {

    @Column(nullable = false, length = 8)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    public PessoaFisicaVO() {
    }

    public PessoaFisicaVO(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public PessoaFisicaVO(String nome, String nome2, String cpf) {
        super(nome);
        nome = nome2;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
