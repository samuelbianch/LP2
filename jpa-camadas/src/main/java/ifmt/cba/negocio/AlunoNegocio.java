package ifmt.cba.negocio;

import java.util.List;

import ifmt.cba.persistencia.AlunoDAO;
import ifmt.cba.persistencia.FabricaEntityManager;
import ifmt.cba.vo.AlunoVO;
import ifmt.cba.persistencia.PersistenciaException;

public class AlunoNegocio {

    private AlunoDAO alunoDAO;

    public AlunoNegocio() throws NegocioException {
        try {
            this.alunoDAO = new AlunoDAO(FabricaEntityManager.getEntityManager());
        } catch (PersistenciaException exception) {
            throw new NegocioException("Erro ao iniciar Entity Manager: " + exception.getMessage());
        }
    }

    public void inserir(AlunoVO alunoVO) throws NegocioException {
        String errorMessage = this.validarDados(alunoVO);

        if (!errorMessage.isEmpty()) {
            throw new NegocioException(errorMessage);
        }

        try {
            alunoDAO.beginTransaction();
            alunoDAO.incluir(alunoVO);
            alunoDAO.commitTransaction();
        } catch (PersistenciaException p) {
            alunoDAO.rollbackTransaction();
            throw new NegocioException("Erro ao incluir aluno: " + p.getMessage());
        }
    }

    public void alterar(AlunoVO alunoVO) throws NegocioException {
        String errorMessage = this.validarDados(alunoVO);

        if (!errorMessage.isEmpty()) {
            throw new NegocioException(errorMessage);
        }

        try {
            alunoDAO.beginTransaction();
            alunoDAO.alterar(alunoVO);
            alunoDAO.commitTransaction();
        } catch (PersistenciaException p) {
            alunoDAO.rollbackTransaction();
            throw new NegocioException("Erro ao alterar aluno: " + p.getMessage());
        }
    }

    public void excluir(AlunoVO alunoVO) throws NegocioException {
        try {
            alunoDAO.beginTransaction();
            alunoDAO.excluir(alunoVO);
            alunoDAO.commitTransaction();
        } catch (PersistenciaException p) {
            alunoDAO.rollbackTransaction();
            throw new NegocioException("Erro ao excluir aluno: " + p.getMessage());
        }
    }

    public List<AlunoVO> pesquisaParteNome(String nome) throws NegocioException {
        try {
            return alunoDAO.buscaPorNome(nome);
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
