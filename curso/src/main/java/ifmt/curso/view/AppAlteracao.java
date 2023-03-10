package ifmt.curso.view;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import ifmt.curso.negocio.CursoNegocio;

public class AppAlteracao {
    public static void main(String[] args) {
        try {
            CursoNegocio cursoNegocio = new CursoNegocio();

            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo do curso que deseja alterar"));

            ResultSet resultado = cursoNegocio.buscaPorCodigo(codigo);

            if (resultado.next()) {
                String nome = JOptionPane.showInputDialog("Nome: ", resultado.getString("nome"));
                int cargahoraria = Integer.parseInt(JOptionPane.showInputDialog("Carga Horaria: ", resultado.getString("cargahoraria")));
                int numsemestre = Integer.parseInt(JOptionPane.showInputDialog("Numero de semestres: ", resultado.getString("numsemestre")));
                cursoNegocio.alterar(resultado.getInt("codigo"), nome, cargahoraria, numsemestre);
            } else {
                System.out.println("Nao localizado");
            }

            resultado.close();
            cursoNegocio.desconectar();
        } catch (Exception exception) {
            System.out.println("Erro ao alterar curso: " + exception.getMessage());
        }
    }
}
