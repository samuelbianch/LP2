package ifmt.cba.vo;

import java.util.Calendar;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class VendaVO {

    @Id
    @Column(nullable = false)
    private int codigo;

    @Column(nullable = false)
    private Calendar dataVenda;

    public VendaVO() {
    }

    public VendaVO(int codigo, Calendar dataVenda) {
        this.codigo = codigo;
        this.dataVenda = dataVenda;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Calendar getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Calendar dataVenda) {
        this.dataVenda = dataVenda;
    }
}
