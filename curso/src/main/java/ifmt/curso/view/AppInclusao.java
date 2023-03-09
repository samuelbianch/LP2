package ifmt.curso.view;

import javax.swing.JOptionPane;

import ifmt.curso.negocio.CursoNegocio;

public class AppInclusao {
    public static void main(String[] args) {
        try {
            CursoNegocio cursoNegocio = new CursoNegocio();

            String nome = JOptionPane.showInputDialog("Nome do curso: ");
            int cargahoraria = Integer.parseInt(JOptionPane.showInputDialog("Carga horaria da disciplina: "));
            int numsemestre = Integer.parseInt(JOptionPane.showInputDialog("Numero de semestre: "));
        
            cursoNegocio.incluir(nome, cargahoraria, numsemestre);
            cursoNegocio.desconectar();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
