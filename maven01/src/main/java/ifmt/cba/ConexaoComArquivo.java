package ifmt.cba;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoComArquivo {
    public static void main(String[] args) {
        
        Properties proDB = new Properties();
        FileInputStream leitorArquivo;

        try {
            leitorArquivo = new FileInputStream("conexao.properties");
            proDB.load(leitorArquivo);
            leitorArquivo.close();

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Falha ao localizar o arquivo" + fileNotFoundException.getMessage());
        } catch (IOException ioException) {
            System.out.println("Erro ao ler o arquivo" + ioException.getMessage());
        }

        if (!proDB.isEmpty()) {
            Connection conexao;
            String url = proDB.getProperty("url");
            String driver = proDB.getProperty("driver");
            String usr = proDB.getProperty("usr");
            String pass = proDB.getProperty("pass");
            
            try {
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, usr, pass);
                System.out.println("Conexao estabelecida");
                conexao.close();
                System.out.println("Conexao encerrada");
            } catch (SQLException sqlException) {
                System.out.println("Conexao nao estabelecida " + sqlException);
            } catch (ClassNotFoundException classNotFoundException){
                System.out.println("Driver nao encontrado ");
            }
        }

        
    }
}
