import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ColecaoDisciplina implements IColecaoGenerica {
    
    ArrayList<Disciplina> listaDisciplina;

    public ColecaoDisciplina(){
        listaDisciplina = new ArrayList<Disciplina>();
    }

    public void adicionaDisciplina(Disciplina disciplina){
        listaDisciplina.add(disciplina);
    }

    public Iterator<Disciplina> getIterator(){
        return listaDisciplina.iterator();
    }

    public void ordena(){
        Collections.sort(listaDisciplina, new ComparaNome());
    }
}
