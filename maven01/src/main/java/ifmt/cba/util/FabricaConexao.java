package ifmt.cba.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/produtos";
    private static final String DRIVE = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASS = "admin";

    public static Connection obterConexao() {
        Connection conexao = null;

        try {
            Class.forName(DRIVE);
            conexao = DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver nao encontrado" + cnf);
        } catch (SQLException sqlException) {
            System.out.println("Conexao nao estabelecida" + sqlException);
        }

        return conexao;
    }
}
