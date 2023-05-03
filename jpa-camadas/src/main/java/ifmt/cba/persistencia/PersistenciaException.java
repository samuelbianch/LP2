package ifmt.cba.persistencia;

public class PersistenciaException extends Exception {

    public PersistenciaException() {
        super("Erro ocorrido na manipulacao do DB");
    }

    public PersistenciaException(String msg) {
        super(msg);
    }
}
