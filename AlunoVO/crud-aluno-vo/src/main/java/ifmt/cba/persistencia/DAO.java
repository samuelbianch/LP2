package ifmt.cba.persistencia;

public class DAO {
    protected ConexaoBD conexao;

    public DAO(ConexaoBD conexao) throws PersistenciaException {
        this.conexao = conexao;
    }

    public ConexaoBD getConexao() {
        return conexao;
    }

    public void SetConexao(ConexaoBD conexao) {
        this.conexao = conexao;
    }

    public void desconectarBD() throws PersistenciaException {
        conexao.desconectar();
    }
}
