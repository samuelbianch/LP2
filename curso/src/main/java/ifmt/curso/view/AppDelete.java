package ifmt.curso.view;

import javax.swing.JOptionPane;
import ifmt.curso.negocio.CursoNegocio;

public class AppDelete {
    public static void main(String[] args) {
        try {
            CursoNegocio cursoNegocio = new CursoNegocio();
    
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do que você deseja deletar"));
    
            cursoNegocio.excluir(id);

            cursoNegocio.desconectar();
        } catch (Exception exception) {
            System.out.println("Erro ao excluir! " + exception.getMessage());
        }
    }
}
