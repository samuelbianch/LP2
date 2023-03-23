package negocio;

import java.util.List;

import persistencia.CursoDAO;
import persistencia.PersistenciaException;
import vo.CursoVO;

public class CursoNegocio {
    private CursoDAO cursoDAO;

    public CursoNegocio() throws NegocioException {
        try {
            this.cursoDAO = new CursoDAO();
        } catch (PersistenciaException persistenciaException) {
            throw new NegocioException("Erro ao preparar persistencia");
        }
    }

    public void incluir(CursoVO cursoVO) throws NegocioException {
        try {
            String validacao = this.validarDados(cursoVO);
            if (validacao.isEmpty()) {
                this.cursoDAO.incluir(cursoVO);
            } else {
                throw new NegocioException("Dados inconsistentes para o cadastro" + validacao);
            }
        }  catch (PersistenciaException persistenciaException) {
            throw new NegocioException("Erro ao incluir curso - " + persistenciaException.getMessage());
        }
    }

    public void alterar(CursoVO cursoVO) throws NegocioException {
        try {
            String validacao = this.validarDados(cursoVO);
            if (validacao.isEmpty()) {
                this.cursoDAO.alterar(cursoVO);
            } else {
                throw new NegocioException("Dados inconsistentes para o cadastro" + validacao);
            }
        }  catch (PersistenciaException persistenciaException) {
            throw new NegocioException("Erro ao alterar curso - " + persistenciaException.getMessage());
        }
    }

    public void excluir(int codigo) throws NegocioException {
        try {
            this.cursoDAO.excluir(codigo);
        } catch (PersistenciaException persistenciaException) {
            throw new NegocioException("Erro ao excluir curso - " + persistenciaException.getMessage());
        }
    }

    public CursoVO buscaPorCodigo(int codigo) throws NegocioException {
        CursoVO cursoVO = null;
        
        try {
            cursoVO = this.cursoDAO.buscaPorCodigo(codigo);
        } catch (PersistenciaException persistenciaException) {
            throw new NegocioException(persistenciaException.getMessage());
        }

        return cursoVO;
    }

    public List<CursoVO> buscaPorNome(String nome) throws NegocioException {
        List<CursoVO> cursoVO = null;
        
        try {
            cursoVO = this.cursoDAO.buscaPorNome(nome);
        } catch (PersistenciaException persistenciaException) {
            throw new NegocioException(persistenciaException.getMessage());
        }

        return cursoVO;
    }

    public List<CursoVO> buscaTodos() throws NegocioException {
        List<CursoVO> cursoVO = null;
        
        try {
            cursoVO = this.cursoDAO.buscaTodos();
        } catch (PersistenciaException persistenciaException) {
            throw new NegocioException(persistenciaException.getMessage());
        }

        return cursoVO;
    }

    public void desconectar() throws NegocioException {
        try {
            this.cursoDAO.descontectar();
        } catch (PersistenciaException persistenciaException) {
            throw new NegocioException("Erro ao se desconectar" + persistenciaException.getMessage());
        }
    }

    private String validarDados(CursoVO cursoVO) {
        String retorno = "";

        if (cursoVO == null) {
            retorno += "Dados do curso nao podem ser nulos";
        } else {
            if (cursoVO.getNome() == null || cursoVO.getNome().isEmpty()){
                retorno += "Nome nao pode ser nulo/vazio";
            } if (cursoVO.getCargahoraria() <= 0) {
                retorno += "Carga horaria tem que ser maior que 0";
            } if (cursoVO.getNumsemestre() <= 0) {
                retorno += "Numero de semestre tem que ser maior que 0";
            }
        }
        return retorno;
    }
}
