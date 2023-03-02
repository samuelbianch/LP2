package ifmt.cba.execusao;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ifmt.cba.util.FabricaConexao;

public class Incluir02 {
    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement comando = null;

        String nome = JOptionPane.showInputDialog("Nome: ");
        float promocao = Float.parseFloat(JOptionPane.showInputDialog("Promocao: "));
        float margem = Float.parseFloat(JOptionPane.showInputDialog("Margem: "));

        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement("INSERT INTO grupoproduto (nome, promocao, margemlucro) VALUES (?, ?, ?)");
            comando.setString(1, nome);
            comando.setFloat(2, promocao);
            comando.setFloat(3, margem);
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inclusao realizada com sucesso");
        } catch (Exception exception){
            JOptionPane.showMessageDialog(null, "Erro ao incluir senteca" + exception.toString());
        } finally {
            try {
                comando.close();
                conexao.close();
            } catch (SQLException sqlException){
                System.out.println("\nErro na conexao com o banco" + sqlException);
            }
        }
    }
}
