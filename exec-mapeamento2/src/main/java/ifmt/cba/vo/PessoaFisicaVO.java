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

}
