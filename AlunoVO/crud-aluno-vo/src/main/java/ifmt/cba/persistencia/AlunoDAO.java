package ifmt.cba.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifmt.cba.vo.AlunoVO;
import ifmt.cba.vo.EnumSexo;
import ifmt.cba.vo.EnumUF;

public class AlunoDAO extends DAO {
    private static PreparedStatement comandoIncluir;
    private static PreparedStatement comandoAlterar;
    private static PreparedStatement comandoExcluir;
    private static PreparedStatement comandoBuscaMatricula;

    public AlunoDAO(ConexaoBD conexao) throws PersistenciaException {
        super(conexao);

        try {
            comandoIncluir = conexao.getConexao().prepareStatement("INSERT INTO aluno (nome, nomemae, nomepai, sexo, " + "logradouro, numero, bairro, cidade, uf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            comandoAlterar = conexao.getConexao().prepareStatement("UPDATE aluno SET nome=?, nomepai=?, nomemae=?, sexo=?, " + "logradouro=?, numero=?, bairro=?, cidade=?, uf=?. WHERE matricula=?");
            comandoExcluir = conexao.getConexao().prepareStatement("DELETE FROM aluno WHERE matricula=?");
            comandoBuscaMatricula = conexao.getConexao().prepareStatement("SELECT * FROM aluno WHERE matricula=?");
        } catch (SQLException exception) {
            throw new PersistenciaException("Erro ao incluir novo aluno " + exception.getMessage());
        }
    }

    public int incluir(ifmt.cba.vo.AlunoVO AlunoVO) throws PersistenciaException {
        int retorno = 0;
        
        try {
            comandoIncluir.setString(1, AlunoVO.getNome());
            comandoIncluir.setString(2, AlunoVO.getNomeMae());
            comandoIncluir.setString(3, AlunoVO.getNomePai());
            comandoIncluir.setInt(4, AlunoVO.getSexo().ordinal());
            comandoIncluir.setString(5, AlunoVO.getEndereco().getLogradouro());
            comandoIncluir.setInt(6, AlunoVO.getEndereco().getNumero());
            comandoIncluir.setString(7, AlunoVO.getEndereco().getBairro());
            comandoIncluir.setString(8, AlunoVO.getEndereco().getCidade());
            comandoIncluir.setString(9, AlunoVO.getEndereco().getUf().name());
            retorno = comandoIncluir.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenciaException("Erro ao incluir - " + exception. getMessage());
        }
        return retorno;
    }

    public int alterar(ifmt.cba.vo.AlunoVO AlunoVO) throws PersistenciaException {
        int retorno = 0;
        
        try {
            comandoAlterar.setString(1, AlunoVO.getNome());
            comandoAlterar.setString(2, AlunoVO.getNomePai());
            comandoAlterar.setString(3, AlunoVO.getNomeMae());
            comandoAlterar.setInt(4, AlunoVO.getSexo().ordinal());
            comandoAlterar.setString(5, AlunoVO.getEndereco().getLogradouro());
            comandoAlterar.setInt(6, AlunoVO.getEndereco().getNumero());
            comandoAlterar.setString(7, AlunoVO.getEndereco().getBairro());
            comandoAlterar.setString(8, AlunoVO.getEndereco().getCidade());
            comandoAlterar.setString(9, AlunoVO.getEndereco().getUf().name());
            comandoAlterar.setInt(10, AlunoVO.getMatricula());
            retorno = comandoAlterar.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenciaException("Erro ao alterar - " + exception. getMessage());
        }

        return retorno;
    }

    public int excluir(int matricula) throws PersistenciaException {
        int retorno = 0;
        
        try {
            comandoExcluir.setInt(1, matricula);
            retorno = comandoExcluir.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenciaException("Erro ao excluir - " + exception. getMessage());
        }
        return retorno;

    }

    public AlunoVO buscaPorMatricula(int matricula) throws PersistenciaException {
        AlunoVO aluno = null;
        
        try {
            comandoBuscaMatricula.setInt(1, matricula);
            ResultSet resultado = comandoBuscaMatricula.executeQuery();

            if (resultado.next()) {
                aluno = this.montaAlunoVO(resultado);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Erro ao buscar por matricula - " + exception. getMessage());
        }
        return aluno;
    }

    public List<AlunoVO> buscarPorNome(String nome) throws PersistenciaException {
        List<AlunoVO> listaAluno = new ArrayList<>();
        AlunoVO aluno = null;

        String comandoSQL = "SELECT * FROM aluno WHERE UPPER(nome) LIKE '" + nome.trim().toUpperCase() + "%' ORDER BY NOME LIMIT 10";

        try {
            PreparedStatement comando = conexao.getConexao().prepareStatement(comandoSQL);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                aluno = this.montaAlunoVO(resultado);
                listaAluno.add(aluno);
            }
            comando.close();
        } catch (Exception ex) {
            throw new PersistenciaException("Erro na selecao por nome: " + ex.getMessage());
        }
        return listaAluno;
    }

    public AlunoVO montaAlunoVO(ResultSet resultado) throws PersistenciaException {
        AlunoVO aluno = new AlunoVO();
        if (resultado != null) {
            try {
                aluno.setMatricula(resultado.getInt("matricula"));
                aluno.setNome("nome");
                aluno.setNomeMae("nomemae");
                aluno.setNomePai("nomepai");
                aluno.setSexo(EnumSexo.values()[resultado.getInt("sexo")]);
                aluno.getEndereco().setLogradouro(resultado.getString("logradouro"));
                aluno.getEndereco().setNumero(resultado.getInt("numero"));
                aluno.getEndereco().setBairro(resultado.getString("bairro"));
                aluno.getEndereco().setCidade(resultado.getString("cidade"));
                aluno.getEndereco().setUf(EnumUF.valueOf(resultado.getString("uf")));
            } catch (Exception exception) {
            throw new PersistenciaException("Erro ao acessar dados do resultado" + exception.getMessage());
            }
        }
        return aluno;
    }
}
