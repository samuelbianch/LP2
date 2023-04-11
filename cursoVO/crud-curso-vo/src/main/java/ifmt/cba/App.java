package ifmt.cba;

import javax.swing.JOptionPane;

import negocio.CursoNegocio;
import negocio.NegocioException;
import vo.CursoVO;

public class App 
{
    private static CursoNegocio cursoNegocio;

    static {
        try {
            cursoNegocio = new CursoNegocio();
        } catch (NegocioException negocioException) {
            JOptionPane.showMessageDialog(null, negocioException.getMessage());
        }
    }
    public static void main( String[] args )
    {
        int selecao;
        do {
            selecao = menu();

            switch (selecao) {
                case 1: processarInclusao();
            }
        } while (selecao != 7);

        try {
            cursoNegocio.desconectar();
        } catch (NegocioException negocioException) {
            JOptionPane.showMessageDialog(null, negocioException.getMessage());
        }

        System.exit(0);
    }

    private static int menu() {
        int selecao = 0;

        String opcoes = "1 - Incluir Curso \n" +
            "2 - Alterar Curso\n" +
            "3 - Excluir Curso\n" +
            "4 - Pesquisar Curso por Codigo" +
            "5 - Pesquisar Curso por nome" +
            "6 - Pesquisar todos os cursos" +
            "7 - Sair";

        do {
            selecao = Integer.parseInt(JOptionPane.showInputDialog(opcoes));
            if (selecao < 1 || selecao > 7) {
                JOptionPane.showMessageDialog(null, "Opcao invalida");
            }
        } while (selecao < 1 || selecao > 7);

        return selecao;
    }

    private static void processarInclusao() {
        try {
            String nome = JOptionPane.showInputDialog("Forneca o nome do curso");
            int cargahoraria = Integer.parseInt(JOptionPane.showInputDialog("Forneca a carga horaria"));
            int numsemestre = Integer.parseInt(JOptionPane.showInputDialog("Forneca o numero de semestres"));
            CursoVO cursoVO = new CursoVO();

            cursoVO.setNome(nome);
            cursoVO.setCargahoraria(cargahoraria);
            cursoVO.setNumsemestre(numsemestre);
            cursoNegocio.incluir(cursoVO);

            JOptionPane.showMessageDialog(null, "Incluido com sucesso");
        } catch (Exception negocioException) {
            System.out.println("Erro ao inserir curso" + negocioException.getMessage());
        }
    }
}
