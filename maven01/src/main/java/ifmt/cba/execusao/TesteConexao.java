package ifmt.cba.execusao;

import java.sql.Connection;
import java.sql.SQLException;

import ifmt.cba.util.FabricaConexao;

public class TesteConexao {
    public static void main(String[] args) {
        Connection conexao;
        try {
            conexao = FabricaConexao.obterConexao();
            if (conexao != null){
                System.out.println("Conexao estabelecida");
                conexao.close();
                System.out.println("Conexao encerrada");
            } else {
                System.out.println("Problemas na conexao -> Dados incorreto");
            }
            
        } catch (SQLException sqlException) {
            System.out.println("Conexao nao estabelecida" + sqlException);
        }
    }
}
