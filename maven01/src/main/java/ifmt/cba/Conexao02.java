package ifmt.cba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;

public class Conexao02 {
    public static void main(String[] args) {
        Connection conexao;

        String url = "jdbc:postgresql://localhost:5432/produtos";
        String usr = "postgres";
        String pass = "admin";

        try {
            DriverManager.registerDriver(new Driver());
            conexao = DriverManager.getConnection(url, usr, pass);
            System.out.println("Conexao estabelecida");
            conexao.close();
            System.out.println("Conexao encerrada");
        } catch (SQLException sqlException) {
            System.out.println("Conexao nao estabelecida" + sqlException);
        }
    }
}
