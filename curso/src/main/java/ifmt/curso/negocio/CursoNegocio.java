package ifmt.curso.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ifmt.curso.util.FabricaConexao;

public class CursoNegocio {
    private Connection conexao;
    private PreparedStatement comandoInclusao;
    private PreparedStatement comandoAlteracao;
    private PreparedStatement comandoExclusao;
    private PreparedStatement comandoSelecaoCodigo;
    private PreparedStatement comandoSelecaoNome;

    public CursoNegocio() throws Exception{
        try {
            this.conexao = FabricaConexao.obterConexao();
            this.comandoInclusao = conexao.prepareStatement("INSERT INTO curso (nome, cargahoraria, numsemestre) VALUES (?, ?, ?)");
            this.comandoAlteracao = conexao.prepareStatement("UPDATE curso SET nome=?, cargahoraria=?, numsemestre=?, codigo=?");
            this.comandoExclusao = conexao.prepareStatement("DELETE FROM curso WHERE codigo=?");
            this.comandoSelecaoCodigo = conexao.prepareStatement("SELECT * FROM curso WHERE codigo=?");
            this.comandoSelecaoNome = conexao.prepareStatement("SELECT * FROM curso WHERE upper(nome) LIKE ?");
        } catch (Exception exception) {
            throw new Exception("Erro ao preparar registro no banco" + exception.getMessage());
        }
    }

    public void alterar(int codigo, String nome, int cargahoraria, int numsemestre) throws Exception{
        try {
            this.comandoAlteracao.setString(1, nome);
            this.comandoAlteracao.setInt(2, cargahoraria);
            this.comandoAlteracao.setInt(3, numsemestre);
            this.comandoAlteracao.setInt(4, codigo);
            this.comandoAlteracao.executeQuery();
        } catch (SQLException sqlException){
            throw new Exception("Erro ao alterar dados: " + sqlException.getMessage());
        }
    }

    public void incluir(String nome, int cargahoraria, int numsemestre) throws Exception{
        try {
            this.comandoInclusao.setString(1, nome);
            this.comandoInclusao.setInt(2, cargahoraria);
            this.comandoInclusao.setInt(3, numsemestre);
            this.comandoInclusao.executeUpdate();
        } catch (SQLException sqlException) {
            throw new Exception("Erro ao inserir registro" + sqlException.getMessage());
        }
    }

    public void excluir(int codigo) throws Exception {
        try {
            this.comandoExclusao.setInt(1, codigo);
            this.comandoExclusao.executeUpdate();
        } catch (SQLException sqlException) {
            throw new Exception("Erro ao excluir curso" + sqlException.getMessage());
        }
    }

    public ResultSet buscaPorCodigo(int codigo) throws Exception {
        ResultSet resultado = null;
        try {
            this.comandoSelecaoCodigo.setInt(1, codigo);
            resultado = this.comandoSelecaoCodigo.executeQuery();
        } catch (SQLException sqlException) {
            throw new Exception("Erro ao buscar por codigo" + sqlException.getMessage());
        }
        
        return resultado;
    }

    public ResultSet buscaPorNome(String nome) throws Exception {
        ResultSet resultado = null;

        try {
            this.comandoSelecaoNome.setString(1, "%" + nome.toUpperCase() + "%");
            resultado = this.comandoSelecaoNome.executeQuery();
        } catch (SQLException sqlException) {
            throw new Exception("Erro ao buscar por nome" + sqlException.getMessage());
        }

        return resultado;
    }

    public ResultSet buscaTodos() throws Exception {
        ResultSet resultado = null;

        try {
            Statement comandoBuscaTodos = this.conexao.createStatement();
            resultado = comandoBuscaTodos.executeQuery("SELECT * FROM curso ORDER BY NOME ASC");
        } catch (SQLException sqlException) {
            throw new Exception("Erro ao buscar todos os curso: " + sqlException.getMessage());
        }

        return resultado;
    }

    public void desconectar() throws Exception {
        try {
            this.comandoAlteracao.close();
            this.comandoExclusao.close();
            this.comandoInclusao.close();
            this.comandoSelecaoCodigo.close();
            this.comandoSelecaoNome.close();
            this.conexao.close();
        } catch (SQLException sqlException) {
            throw new Exception("Erro ao fechar conexao" + sqlException.getMessage());
        }
    }
}
