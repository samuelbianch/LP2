package ifmt.cba.execusao;

import java.sql.Connection;

import ifmt.cba.util.FabricaConexao;

public class TesteConexao {
    public static void main(String[] args) {
        Connection conexao;
        try { 
            conexao = FabricaConexao.obterConexao();
            System.out.println("Conexao estabelecida");
            conexao.close();
            System.out.println("Conexao encerrada");
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
