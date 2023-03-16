package ifmt.curso.view;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import ifmt.curso.negocio.CursoNegocio;

public class AppSelecaoCodigo {
    public static void main(String[] args) {
        try {
            CursoNegocio cursoNegocio = new CursoNegocio();
    
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o código: "));
    
            ResultSet resultado = cursoNegocio.buscaPorCodigo(id);
    
            if (resultado.next()) {
                System.out.println("Codigo: " + resultado.getString("codigo"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("Carga Horaria: " + resultado.getString("cargahoraria"));
                System.out.println("Numero de semestre: " + resultado.getString("numsemestre"));
            }
    
            resultado.close();
            cursoNegocio.desconectar();
        } catch (Exception exception) {
            System.out.println("Erro ao buscar por codigo: " + exception.getMessage());
        }
    }
}
