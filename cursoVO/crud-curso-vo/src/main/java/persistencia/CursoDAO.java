package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.FabricaConexao;
import vo.CursoVO;

public class CursoDAO {
    private Connection conexao;
    private PreparedStatement comandoInclusao;
    private PreparedStatement comandoAlteracao;
    private PreparedStatement comandoExclusao;
    private PreparedStatement comandoSelecaoCodigo;
    private PreparedStatement comandoSelecaoNome;

    public CursoDAO() throws PersistenciaException {
        try {
            this.conexao = FabricaConexao.obterConexao();
            this.comandoInclusao = conexao.prepareStatement("INSERT INTO curso (nome, cargahoraria, numsemestre) VALUES (?, ?, ?)");
            this.comandoAlteracao = conexao.prepareStatement("UPDATE curso SET nome=?, cargahoraria=?, numsemestre=? WHERE codigo=?");
            this.comandoExclusao = conexao.prepareStatement("DELETE FROM curso WHERE codigo=?");
            this.comandoSelecaoCodigo = conexao.prepareStatement("SELECT * FROM curso WHERE codigo=?");
            this.comandoSelecaoNome = conexao.prepareStatement("SELECT * FROM curso WHERE upper(nome) LIKE ?");
        } catch (Exception exception) {
            throw new PersistenciaException("Erro ao preparar persistencia - " + exception.getMessage());
        }
    }

    public void incluir(CursoVO cursoVO) throws PersistenciaException {
        try {
            this.comandoInclusao.setString(1, cursoVO.getNome());
            this.comandoInclusao.setInt(2, cursoVO.getCargahoraria());
            this.comandoInclusao.setInt(3, cursoVO.getNumsemestre());
            this.comandoInclusao.executeUpdate();
        } catch (SQLException sqlException) {
            throw new PersistenciaException("Erro ao incluir curso - " + sqlException.getMessage());
        }
    }

    
    public void alterar(CursoVO cursoVO) throws PersistenciaException {
        try {
            this.comandoAlteracao.setString(1, cursoVO.getNome());
            this.comandoAlteracao.setInt(2, cursoVO.getCargahoraria());
            this.comandoAlteracao.setInt(3, cursoVO.getNumsemestre());
            this.comandoAlteracao.setInt(4, cursoVO.getCodigo());
            this.comandoAlteracao.executeUpdate();
        } catch (SQLException sqlException) {
            throw new PersistenciaException("Erro ao alterar curso - " + sqlException.getMessage());
        }
    }

    
    public void excluir(int codigo) throws PersistenciaException {
        try {
            this.comandoExclusao.setInt(1, codigo);
            this.comandoExclusao.executeUpdate();
        } catch (SQLException sqlException) {
            throw new PersistenciaException("Erro ao excluir curso - " + sqlException.getMessage());
        }
    }

    public CursoVO buscaPorCodigo(int codigo) throws PersistenciaException {
        ResultSet resultado = null;
        CursoVO curso_retornado = null;

        try {
            this.comandoSelecaoCodigo.setInt(1, codigo);
            resultado = this.comandoSelecaoCodigo.executeQuery();

            if(resultado.next()) {
                curso_retornado = this.montaVO(resultado);
            }
        } catch (SQLException sqlException) {
            throw new PersistenciaException("Erro ao selecionar curso por codigo - " + sqlException.getMessage());
        }
        return curso_retornado;
    }

    public List<CursoVO> buscaPorNome(String nome) throws PersistenciaException {
        ResultSet resultado = null;
        List<CursoVO> lista_curso_retornado = new ArrayList<CursoVO>();

        try {
            this.comandoSelecaoCodigo.setString(1, "%" + nome.toUpperCase() + "%");
            resultado = this.comandoSelecaoCodigo.executeQuery();
            while (resultado.next()){
                lista_curso_retornado.add(this.montaVO(resultado));
            }
        } catch (SQLException sqlException) {
            throw new PersistenciaException("Erro ao selecionar curso por nome - " + sqlException.getMessage());
        }
        return lista_curso_retornado;
    }

    public List<CursoVO> buscaTodos() throws PersistenciaException {
        ResultSet resultado = null;
        List<CursoVO> lista_curso_retornado = new ArrayList<CursoVO>();

        try {
            Statement comandoBuscaTodos = this.conexao.createStatement();
            resultado = comandoBuscaTodos.executeQuery("SELECT * FROM curso ORDER BY nome");
            while (resultado.next()){
                lista_curso_retornado.add(this.montaVO(resultado));
            }
        } catch (SQLException sqlException) {
            throw new PersistenciaException("Erro ao selecionar todos os cursos - " + sqlException.getMessage());
        }
        return lista_curso_retornado;
    }

    private CursoVO montaVO(ResultSet resultado) throws PersistenciaException {
        CursoVO cursoVO = new CursoVO();

        try {
            cursoVO.setCodigo(resultado.getInt("codigo"));
            cursoVO.setNome(resultado.getString("nome"));
            cursoVO.setCargahoraria(resultado.getInt("cargahoraria"));
            cursoVO.setNumsemestre(resultado.getInt("numsemestre"));
        } catch (SQLException sqlException) {
            throw new PersistenciaException("Erro ao montar objeto CursoVO no montaVO() - " + sqlException.getMessage());
        }

        return cursoVO;
    }

    public void descontectar() throws PersistenciaException {
        try {
            this.comandoAlteracao.close();
            this.comandoExclusao.close();
            this.comandoInclusao.close();
            this.comandoSelecaoCodigo.close();
            this.comandoSelecaoNome.close();
            this.conexao.close();
        } catch (SQLException sqlException) {
            throw new PersistenciaException("Erro ao se desconectar - " + sqlException.getMessage());
        }
    }
}
