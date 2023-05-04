package ifmt.cba.persistencia;

import java.util.ArrayList;
import java.util.List;

import ifmt.cba.vo.AlunoVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class AlunoDAO extends DAO<AlunoVO> {
    public AlunoDAO(EntityManager entityManager) throws PersistenciaException {
        super(entityManager);
    }

    public AlunoVO buscaPorMatricula(int matricula) throws PersistenciaException {
        AlunoVO aluno = null;

        try {
            aluno = this.entityManager.find(AlunoVO.class, matricula);
        } catch (Exception e) {
            throw new PersistenciaException("Erro ao buscar aluno por matricula: " + e.getMessage());
        }
        return aluno;
    }

    @SuppressWarnings("unchecked")
    public List<AlunoVO> buscaPorNome(String nome) throws PersistenciaException {
        List<AlunoVO> listaAluno = new ArrayList<AlunoVO>();

        try {
            Query query = this.entityManager
                    .createQuery("SELECT a FROM AlunoVO a WHERE UPPER(a.nome) LIKE :pNome ORDER BY a.nome");
            query.setParameter("pNome", "%" + nome.toUpperCase().trim() + "%");
            listaAluno = query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Erro ao buscar por nome: " + e.getMessage());
        }

        return listaAluno;
    }
}
