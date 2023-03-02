package ifmt.cba.execusao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import ifmt.cba.util.FabricaConexao;

public class Incluir {
    public static void main(String[] args) {
        Connection conexao = null;
        Statement comando = null;

        String nome = JOptionPane.showInputDialog("Nome: ");
        float promocao = Float.parseFloat(JOptionPane.showInputDialog("Promocao: "));
        float margem = Float.parseFloat(JOptionPane.showInputDialog("Margem: "));

        String sql = "INSERT INTO grupoproduto (nome, promocao, margemlucro) VALUES ";
        sql += "('" + nome + "', " + promocao + ", " + margem + ")";
        
        System.out.println("\n" + sql + "\n");
        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.createStatement();
            comando.executeUpdate(sql);
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
