package ifmt.curso.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import ifmt.curso.negocio.CursoNegocio;

public class AppSelecaoTudo {
    public static void main(String[] args) {
        
        try {
            CursoNegocio cursoNegocio = new CursoNegocio();
            ResultSet resultado = cursoNegocio.buscaTodos();

            while (resultado.next()) {
                System.out.println("\n------------------------");
                System.out.println("Codigo: " + resultado.getString("codigo"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("Carga Horaria: " + resultado.getString("cargahoraria"));
                System.out.println("Numero de semestre: " + resultado.getString("numsemestre"));
                System.out.println("------------------------");
            }

            resultado.close();
            cursoNegocio.desconectar();
        } catch (Exception exception) {
            System.out.println("Erro ao buscar todos os registros " + exception.getMessage());
        }
    }
}
