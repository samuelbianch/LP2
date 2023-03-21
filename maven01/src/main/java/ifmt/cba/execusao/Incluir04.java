package ifmt.cba.execusao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import ifmt.cba.util.FabricaConexao;

public class Incluir04 {
    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = FabricaConexao.obterConexao();
            Map<String, Integer> listaGrupos = obterGruposProdutos(conexao);

            String nome = JOptionPane.showInputDialog("Digite o nome do produto");
            int estoque = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque"));
            float valorCompra = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor da compra"));
            float promocao = Float.parseFloat(JOptionPane.showInputDialog("Digite o percentual da promocao do produto"));
            float margem = Float.parseFloat(JOptionPane.showInputDialog("Digite o percentual de margem de lucro do produto"));

            String nomeGrupo = (String) JOptionPane.showInputDialog(null, "Escolha o grupo de produto",
                "Grupo de produto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listaGrupos.keySet().toArray(),
                listaGrupos.keySet().toArray()[0]
            );

            comando = conexao.prepareStatement("INSERT INTO produto ( nome, estoque, valorcompra, promocao, margemlucro, grupo ) VALUES (?, ?, ?, ?, ?, ?)");
            comando.setString(1, nome);
            comando.setInt(2, estoque);
            comando.setFloat(3, valorCompra);
            comando.setFloat(4, promocao);
            comando.setFloat(5, margem);
            comando.setInt(6, listaGrupos.get(nomeGrupo));
            comando.executeUpdate ();
            System.out.println("Inclusao do produto realizada com sucesso");
        } catch (Exception exception) {
            System.out.println("Erro ao incluir produto: " + exception.getMessage());
        } finally {
            try {
                comando.close();
                conexao.close();
            } catch (SQLException sqlException) {
                System.out.println("Erro ao desconectar: " + sqlException.getMessage());
            }
        }
    }

    public static Map<String, Integer> obterGruposProdutos(Connection conexao) {
        Map<String, Integer> listaGrupos = new HashMap<String, Integer>();
        PreparedStatement comando = null;

        try {
            comando = conexao.prepareStatement("SELECT * FROM grupoproduto ORDER BY nome");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                listaGrupos.put(resultado.getString("nome"), resultado.getInt("codigo"));
            }

            resultado.close();
        } catch (SQLException sqlException) {
            System.out.println("SQL Exception: " + sqlException.getMessage());
        } finally { 
            try {
                comando.close();
            } catch (SQLException sqlException) {
                System.out.println("Erro ao desconectar: " + sqlException.getMessage());
            }
        }

        return listaGrupos;
    }
}
