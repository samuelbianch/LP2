package ifmt.cba.vo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "venda")
public class VendaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo;

    @Column(name = "datavenda", nullable = false)
    private Date dataVenda;

    @ManyToOne
    private ClienteVO clienteVO;

    @ManyToOne
    private VendedorVO vendedor;

    public VendaVO() {
    }

    public VendaVO(Date dataVenda, VendedorVO vendedor, ClienteVO cliente) {
        this.dataVenda = dataVenda;
        this.vendedor = vendedor;
        this.clienteVO = cliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public VendedorVO getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorVO vendedor) {
        this.vendedor = vendedor;
    }

    public ClienteVO getClienteVO() {
        return clienteVO;
    }

    public void setClienteVO(ClienteVO clienteVO) {
        this.clienteVO = clienteVO;
    }
}
