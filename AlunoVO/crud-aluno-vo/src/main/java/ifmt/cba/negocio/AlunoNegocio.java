package ifmt.cba.negocio;

import java.util.List;

import ifmt.cba.persistencia.AlunoDAO;
import ifmt.cba.persistencia.ConexaoBD;
import ifmt.cba.persistencia.PersistenciaException;
import ifmt.cba.vo.AlunoVO;

public class AlunoNegocio {
    private AlunoDAO alunoDAO;

    public AlunoNegocio() throws NegocioException {
        try {
            alunoDAO = new AlunoDAO(ConexaoBD.getInstancia());    
        } catch (PersistenciaException exception) {
            throw new NegocioException("Erro ao iniciar instancia: " + exception.getMessage());
        }
    }

    public void inserir(AlunoVO alunoVO) throws NegocioException {
        String msg = this.validarDados(alunoVO);

        if (!msg.isEmpty()) {
            throw new NegocioException(msg);
        } else {
            try {
                if (alunoDAO.incluir(alunoVO) == 0) {
                    throw new NegocioException("Inclusao nao realizada");
                }
            } catch (PersistenciaException exception) {
                throw new NegocioException("Erro ao incluir aluno: " + exception.getMessage());
            }
        }
    }

    public void alterar(AlunoVO alunoVO) throws NegocioException {
        String msg = this.validarDados(alunoVO);

        if (!msg.isEmpty()) {
            throw new NegocioException(msg);
        } else {
            try {
                if (alunoDAO.alterar(alunoVO) == 0) {
                    throw new NegocioException("Inclusao nao realizada");
                }
            } catch (PersistenciaException exception) {
                throw new NegocioException("Erro ao alterar aluno: " + exception.getMessage());
            }
        }
    }

    public void excluir(int codigo) throws NegocioException {
        try {
            if (alunoDAO.excluir(codigo) == 0) {
                throw new NegocioException("Inclusao nao realizada");
            }
        } catch (PersistenciaException exception) {
            throw new NegocioException("Erro ao excluir aluno: " + exception.getMessage());
        }
    }

    public List<AlunoVO> pesquisaParteNome(String nome) throws NegocioException {
        try {
            return alunoDAO.buscarPorNome(nome);
        } catch (PersistenciaException exception) {
            throw new NegocioException("Erro ao perquisar aluno pelo nome: " + exception.getMessage());
        }
    }

    public AlunoVO pesquisaPorMatricula(int matricula) throws NegocioException {
        try {
            return alunoDAO.buscaPorMatricula(matricula);
        } catch (PersistenciaException exception) {
            throw new NegocioException("Erro ao perquisar aluno pela matricula: " + exception.getMessage());
        }
    }

    private String validarDados(AlunoVO alunoVO) {
        String msg = "";

        if (alunoVO.getNome() == null || alunoVO.getNome().length() == 0) {
            msg += "Nome nao pode ser vazio";
        }
        if (alunoVO.getNomeMae() == null || alunoVO.getNomeMae().length() == 0) {
            msg += "\nNome da mae nao pode ser vazio";
        }
        if (alunoVO.getNomePai() == null || alunoVO.getNomePai().length() == 0) {
            msg += "\nNome do pai nao pode ser vazio";
        }
        if (alunoVO.getSexo() == null) {
            msg += "\nSexo nao pode ser null";
        }
        if (alunoVO.getEndereco().getLogradouro() == null || alunoVO.getEndereco().getLogradouro().length() == 0) {
            msg += "\nLogradouro nao pode ser vazio";
        }
        if (alunoVO.getEndereco().getBairro() == null || alunoVO.getEndereco().getBairro().length() == 0) {
            msg += "\nBairro nao pode ser vazio";
        }
        if (alunoVO.getEndereco().getCidade() == null || alunoVO.getEndereco().getCidade().length() == 0) {
            msg += "\nCidade nao pode ser vazia";
        }
        if (alunoVO.getEndereco().getNumero() <= 0) {
            msg += "\nNumero nao pode ser menor ou igual a zero";
        }
        if (alunoVO.getEndereco().getUf() == null) {
            msg += "\nUF nao pode ser vazio";
        }

        return msg;
    }
}
