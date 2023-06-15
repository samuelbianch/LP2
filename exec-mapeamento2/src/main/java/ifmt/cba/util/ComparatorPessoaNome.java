package ifmt.cba.util;

import java.util.Comparator;

import ifmt.cba.vo.PessoaVO;

public class ComparatorPessoaNome implements Comparator<PessoaVO> {

    @Override
    public int compare(PessoaVO o1, PessoaVO o2) {
        return o1.getNome().compareTo(o2.getNome());
    }

}