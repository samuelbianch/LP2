package ifmt.cba.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private Connection con;
    private static ConexaoBD instancia;

    private ConexaoBD() throws PersistenciaException {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/academico";
            con = DriverManager.getConnection(url, "postgres", "admin");
        } catch (SQLException sqlException) {
            throw new PersistenciaException("Erro ao conectar com o banco de dados" + sqlException.getMessage());
        } catch (ClassNotFoundException exception) {
            throw new PersistenciaException("Driver nao encontrado" + exception.getMessage());
        }
    }

    public static ConexaoBD getInstancia() throws PersistenciaException {
        if (instancia == null) {
            instancia = new ConexaoBD();
        }

        return instancia;
    }

    public Connection getConexao() {
        return con;
    }

    public void desconectar() throws PersistenciaException {
        try {
            con.close();
        } catch (SQLException exception) {
            throw new PersistenciaException("Erro ao se desconectar do banco de dados" + exception.getMessage());
        }
    }
}
