package ifmt.cba.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoajuridica")
public class PessoaJuridicaVO extends PessoaVO {

    @Column(length = 100, name = "razaosocial")
    private String razaoSocial;

    @Column(length = 100, name = "nomefantasia")
    private String nomeFantasia;

    public PessoaJuridicaVO(String razaoSocial, String nomeFantasia) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public PessoaJuridicaVO(int codigo, String nome, String razaoSocial, String nomeFantasia) {
        super(codigo, nome);
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return this.getNome();
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.setNome(nomeFantasia);
    }

}
