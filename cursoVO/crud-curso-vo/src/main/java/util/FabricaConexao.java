package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/academico";
    private static final String DRIVE = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASS = "admin";

    public static Connection obterConexao() throws Exception {
        Connection conexao = null;

        try {
            Class.forName(DRIVE);
            conexao = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException classNotFoundException) {
            throw new Exception("Driver nao encontrado: " + classNotFoundException.getMessage());
        } catch (SQLException sqlException) {
            throw new Exception("Erro ao conectar com o banco de dados: " + sqlException.getMessage());
        }

        return conexao;
    }
}
