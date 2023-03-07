package ifmt.cba.execusao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import ifmt.cba.util.FabricaConexao;

public class SelecaoPorNome {
    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement comando = null;

        String nomePesquisa = JOptionPane.showInputDialog("Nome para pesquisa");
        System.out.println("Nome para pesquisar: " + nomePesquisa);
        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement("SELECT * FROM grupoproduto WHERE upper(nome) LIKE ?");
            comando.setString(1, "'%" + nomePesquisa.toUpperCase() + "%'");

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()){
                do {
                    System.out.println("\nCodigo: " + resultado.getInt("codigo"));
                    System.out.println("Nome: " + resultado.getString("nome"));
                    System.out.println("Promocao: " + resultado.getFloat("promocao"));
                    System.out.println("Margem de Lucro" + resultado.getFloat("margemlucro"));
                    System.out.println("\n-----------------------------------------------");
                } while (resultado.next());
            } else {
                System.out.println("Não encontrado");
            }

            resultado.close();
        } catch (Exception exception) {
            System.out.println("Erro ao procurar por nome: " + exception.getMessage());
        } finally {
            try {
                comando.close();
            } catch (Exception exceptionClose) {
                System.out.println("Exception ao fechar conexao: " + exceptionClose.getMessage());
            }
        }
    }
}
