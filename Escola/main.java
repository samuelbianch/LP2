public class main {
    public static void main(String[] args) {
        Aluno aluno;
        ColecaoAluno colecaoAluno = new ColecaoAluno();

        ImprimeColecao relatorioAlunos;
        
        aluno = new Aluno();
        aluno.setNome("Ricardo");
        aluno.setMatricula(201);
        aluno.setSemestre(7);
        colecaoAluno.adicionaAluno(aluno);

        aluno = new Aluno();
        aluno.setNome("Alberto");
        aluno.setMatricula(555);
        aluno.setSemestre(8);
        colecaoAluno.adicionaAluno(aluno);

        aluno = new Aluno();
        aluno.setNome("Mariana");
        aluno.setMatricula(187);
        aluno.setSemestre(9);
        colecaoAluno.adicionaAluno(aluno);

        aluno = new Aluno();
        aluno.setNome("Giovanna");
        aluno.setMatricula(478);
        aluno.setSemestre(3);
        colecaoAluno.adicionaAluno(aluno);

        colecaoAluno.ordena();
        relatorioAlunos = new ImprimeColecao(colecaoAluno);
        relatorioAlunos.imprimir();
    }
}
