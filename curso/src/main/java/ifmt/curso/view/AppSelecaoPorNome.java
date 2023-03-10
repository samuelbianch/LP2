package ifmt.curso.view;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import ifmt.curso.negocio.CursoNegocio;

public class AppSelecaoPorNome {
    public static void main(String[] args) {
        try {
            CursoNegocio cursoNegocio = new CursoNegocio();

            String nome = JOptionPane.showInputDialog("Nome para buscar:");

            ResultSet resultado = cursoNegocio.buscaPorNome(nome);

            while (resultado.next()) {
                System.out.println("Codigo: " + resultado.getString("codigo"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("Carga Horaria: " + resultado.getString("cargahoraria"));
                System.out.println("Numero de semestre: " + resultado.getString("numsemestre"));
            }

            resultado.close();
            cursoNegocio.desconectar();
        } catch (Exception exception) {
            System.out.println("Erro ao buscar por nome: " + exception.getMessage());
        }
    }
}
