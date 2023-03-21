package ifmt.cba.execusao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ifmt.cba.util.FabricaConexao;

public class Selecao5 {
    private static Connection conexao;
    private static PreparedStatement comando;

    static {
        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement("SELECT * FROM grupoproduto WHERE codigo=?");
        } catch (Exception exception) {
            System.out.println("Erro ao preparar comando: " + exception.getMessage());
        }
    }

    public static void main(String[] args) {
        PreparedStatement comandoSelecaoProduto = null;
        try {
            comandoSelecaoProduto = conexao.prepareStatement("SELECT * FROM produto ORDER BY nome");
            ResultSet resultado = comandoSelecaoProduto.executeQuery();

            while (resultado.next()) {
                System.out.println("\n------------------------");
                System.out.println("Codigo: .........." + resultado.getString("codigo"));
                System.out.println("Nome: ............" + resultado.getString("nome"));
                System.out.println("Estoque: ........." + resultado.getFloat("estoque"));
                System.out.println("Valor da compra: ." + resultado.getFloat("valorcompra"));
                System.out.println("% Promocao: ......" + resultado.getString("promocao"));
                System.out.println("% Margem lucro: .." + resultado.getString("margemlucro"));
                System.out.println("Grupo: ..........." + obterNomeGrupoProduto(resultado.getInt("grupo")));
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
    public static String obterNomeGrupoProduto(int codigo) {
        String nomeGrupo = null;

        try {
            comando.setInt(1, codigo);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                nomeGrupo = resultado.getString("nome");
            }
        } catch (SQLException sqlException) {
            System.out.println("Erro ao recuperar grupo do produto: " + sqlException.getMessage());
        }

        return nomeGrupo;
    }
}
