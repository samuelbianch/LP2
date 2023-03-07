package ifmt.cba.execusao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import ifmt.cba.util.FabricaConexao;

public class Selecao2 {
    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement comando = null;

        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Codigo para pesquisa"));
        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement("SELECT * FROM grupoproduto WHERE codigo = ?");
            comando.setInt(1, codigo);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()){
                System.out.println("Nome com o codigo: " + resultado.getString("nome"));
            } else {
                System.out.println("Não encontrado");
            }

            resultado.close();
        } catch (Exception exception) {
            System.out.println("Erro ao procurar codigo: " + exception.getMessage());
        } finally {
            try {
                comando.close();
            } catch (Exception exceptionClose) {
                System.out.println("Exception ao fechar conexao: " + exceptionClose.getMessage());
            }
        }
    }
}
