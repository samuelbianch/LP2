package ifmt.cba.execusao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ifmt.cba.util.FabricaConexao;

public class Selecao1 {
    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement("SELECT * FROM grupoproduto ORDER BY nome");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()){
                System.out.println("\nCodigo: " + resultado.getInt("codigo"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("Promocao: " + resultado.getFloat("promocao"));
                System.out.println("Margem de Lucro" + resultado.getFloat("margemlucro"));
                System.out.println("\n-----------------------------------------------");
            }

            resultado.close();
        } catch (Exception exception) {
            System.out.println("Erro ao recuperar produtos" + exception.getMessage());
        } finally {
            try {
                comando.close();
            } catch (Exception exceptionClose) {
                System.out.println("Erro ao fechar conexao: " + exceptionClose.getMessage());
            }
        }
    }

}
