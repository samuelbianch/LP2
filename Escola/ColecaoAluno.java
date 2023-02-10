import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ColecaoAluno extends Aluno implements IColecaoGenerica {

    ArrayList<Aluno> listaAluno;

    public ColecaoAluno(){
        listaAluno = new ArrayList<Aluno>();
    }

    public void adicionaAluno(Aluno aluno){
        listaAluno.add(aluno);
    }

    public Iterator<Aluno> getIterator(){
        return listaAluno.iterator();
    }

    public void ordena(){
        Collections.sort(listaAluno, new ComparaNome());
    }
}
