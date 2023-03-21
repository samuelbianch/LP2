package ifmt.cba.execusao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ifmt.cba.util.FabricaConexao;

public class Selecao4 {
    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement("SELECT * FROM produto ORDER BY nome");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                System.out.println("\n------------------------");
                System.out.println("Codigo: " + resultado.getString("codigo"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("Estoque: " + resultado.getFloat("estoque"));
                System.out.println("Valor da compra: " + resultado.getFloat("valorcompra"));
                System.out.println("Promocao: " + resultado.getString("promocao"));
                System.out.println("Margem lucro: " + resultado.getString("margemlucro"));
                System.out.println("Grupo: " + resultado.getInt("grupo"));
                System.out.println("------------------------");
            }
        } catch (Exception exception) {
            System.out.println("Erro ao recuperar produtos: " + exception.getMessage());
        } finally {
            try {
                comando.close();
                conexao.close();
            } catch (SQLException sqlException) {
            System.out.println("Erro ao se descontectar: " + sqlException.getMessage());
            }
        }
    }
}
