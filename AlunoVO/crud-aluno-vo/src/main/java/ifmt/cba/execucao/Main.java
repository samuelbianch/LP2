package ifmt.cba.execucao;

import java.util.List;

import javax.swing.JOptionPane;

import ifmt.cba.negocio.AlunoNegocio;
import ifmt.cba.negocio.NegocioException;
import ifmt.cba.vo.AlunoVO;
import ifmt.cba.vo.EnumSexo;
import ifmt.cba.vo.EnumUF;

public class Main {
    private static AlunoNegocio alunoNegocio;

    public static void main(String[] args) {
        try {
            alunoNegocio = new AlunoNegocio();
        } catch (NegocioException exception) {
            System.out.println("Erro ao instanciar aluno negocio");
        }
        
        if (alunoNegocio != null) {
            EnumMenu opcao = EnumMenu.Sair;

            do {
                try {
                    opcao = exibirMenu();
                    switch (opcao) {
                        case IncluirAluno:
                            incluirAluno();
                            break;

                        case AlterarAluno:
                            alterarAluno();
                            break;

                        case ExcluirAluno:
                            excluirAluno();
                            break;

                        case PerquisarPorNome:
                            pesquisarPorNome();
                            break;

                        case PesquisarPorMatricula:
                            pesquisarPorMatricula();
                            break;

                        case Sair:
                            break;

                        default:
                            break;
                    }
                } catch (NegocioException exception) {
                    System.out.println("Erro ao escolher a opcao" + exception.getMessage());
                }
            } while (opcao != EnumMenu.Sair);
        }
        System.exit(0);
    }

    // Responsável por inserir um registro de aluno
    private static void incluirAluno() throws NegocioException {
        AlunoVO alunoTemp = lerDados();
        alunoNegocio.inserir(alunoTemp);
    }

    private static void alterarAluno() throws NegocioException {
        int matricula = getMatricula();

        AlunoVO alunoVO = alunoNegocio.pesquisaPorMatricula(matricula);

        if (alunoVO != null) {
            AlunoVO alunoTemp = lerDados(alunoVO);
            alunoNegocio.alterar(alunoTemp);
        } else {
            JOptionPane.showMessageDialog(null , "ERRO: Aluno nao encontrado");
        }
    }

    private static void excluirAluno() throws NegocioException {
        int matricula = getMatricula();

        AlunoVO alunoVO = alunoNegocio.pesquisaPorMatricula(matricula);

        if (alunoVO != null) {
            alunoNegocio.excluir(alunoVO.getMatricula());
        } else {
            JOptionPane.showMessageDialog(null , "Erro ao excluir aluno");
        }
    }

    private static void pesquisarPorMatricula() throws NegocioException {
        int matricula = getMatricula();
        
        AlunoVO alunoVO = alunoNegocio.pesquisaPorMatricula(matricula);

        if (alunoVO != null) {
            mostrarDados(alunoVO);
        } else {
            JOptionPane.showMessageDialog(null , "Aluno nao localizado");
        }
    }

    public static void pesquisarPorNome() throws NegocioException {
        String nome = JOptionPane.showInputDialog(null, "Digite nome a ser buscado", "Leitura da dados", JOptionPane.QUESTION_MESSAGE);

        if (nome != null) {
            List<AlunoVO> listaAluno = alunoNegocio.pesquisaParteNome(nome);

            if (!listaAluno.isEmpty()) {
                for (AlunoVO alunoVO : listaAluno) {
                    mostrarDados(alunoVO);
                }
            } else {
                JOptionPane.showMessageDialog(null , "Aluno nao localizado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nome nao pode ser nulo");
        }
    }


    // Responsavel por exibir os dados do aluno
    private static void mostrarDados(AlunoVO alunoVO) {
        if (alunoVO != null) {
            System.out.println("Matricula: ......." + alunoVO.getMatricula());
            System.out.println("Nome: ............" + alunoVO.getNome());
            System.out.println("Nome da mae: ....." + alunoVO.getNomeMae());
            System.out.println("Nome do pai: ....." + alunoVO.getNomePai());
            System.out.println("Sexo: ............" + alunoVO.getSexo());
            if (alunoVO.getEndereco() != null) {
                System.out.println("Logradouro: ......" + alunoVO.getEndereco().getLogradouro());
                System.out.println("Numero: .........." + alunoVO.getEndereco().getNumero());
                System.out.println("Bairro: .........." + alunoVO.getEndereco().getBairro());
                System.out.println("Cidade: .........." + alunoVO.getEndereco().getCidade());
                System.out.println("UF: .............." + alunoVO.getEndereco().getUf());
                System.out.println("___________________________________________________");
            }
        }
    }

    private static AlunoVO lerDados(AlunoVO alunoTemp) {
        String nome, nomeMae, nomePai, logradouro, bairro, cidade;
        int numero;
        EnumSexo sexo;
        EnumUF uf;

        try {
            nome = JOptionPane.showInputDialog("Forneca o nome: ", alunoTemp.getNome().trim());
            alunoTemp.setNome(nome);

            nomeMae = JOptionPane.showInputDialog("Forneca o nome da mae: ", alunoTemp.getNomeMae().trim());
            alunoTemp.setNomeMae(nomeMae);

            nomePai = JOptionPane.showInputDialog("Forneca o nome do pai: ", alunoTemp.getNomePai().trim());
            alunoTemp.setNomePai(nomePai);

            sexo = (EnumSexo) JOptionPane.showInputDialog(null, "Escolha uma opcao: ", "Leitura de dados", JOptionPane.QUESTION_MESSAGE, null, EnumSexo.values(), alunoTemp.getSexo());
            alunoTemp.setSexo(sexo);

            logradouro = JOptionPane.showInputDialog("Forneca o logradouro: ", alunoTemp.getEndereco().getLogradouro().trim());
            alunoTemp.getEndereco().setLogradouro(logradouro);

            numero = Integer.parseInt(JOptionPane.showInputDialog("Forneca o numero do endereco: ", alunoTemp.getEndereco().getNumero()));
            alunoTemp.getEndereco().setNumero(numero);

            bairro = JOptionPane.showInputDialog("Forneca o bairro: ", alunoTemp.getEndereco().getBairro().trim());
            alunoTemp.getEndereco().setBairro(bairro);

            cidade = JOptionPane.showInputDialog("Forneca a cidade", alunoTemp.getEndereco().getCidade().trim());
            alunoTemp.getEndereco().setCidade(cidade);

            uf = (EnumUF) JOptionPane.showInputDialog(null, "Escolha uma opcao: ", "Leitura de dados", JOptionPane.QUESTION_MESSAGE, null, EnumUF.values(), alunoTemp.getEndereco().getUf());
            alunoTemp.getEndereco().setUf(uf);;
        } catch (Exception exception) {
            System.out.println("Erro de digitacao: " + exception.getMessage());
        }

        return alunoTemp;
    }

    private static AlunoVO lerDados() {
        AlunoVO alunoTemp = new AlunoVO();
        return lerDados(alunoTemp);
    }

    private static EnumMenu exibirMenu() {
        EnumMenu opcao;

        opcao = (EnumMenu) JOptionPane.showInputDialog(null, "Escolha uma opcao: ", "Leitura de dados", JOptionPane.QUESTION_MESSAGE, null, EnumMenu.values(), EnumMenu.values()[0]);

        if (opcao == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma opcao escolhida");
            opcao = EnumMenu.Sair;
        }
        return opcao;
    }

    // Responsavel por consultar a matricula para os metodos que usam matricula para WHERE
    public static int getMatricula() {
        int matricula = 0;

        try {
            matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Forneca a matricula", "Leitura de dados", JOptionPane.QUESTION_MESSAGE));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro de digitacao: " + ex.getMessage());
        } 

        return matricula;
    }

}
