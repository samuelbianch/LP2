package negocio;

public class NegocioException extends Exception{
    public NegocioException() {
        super("Erro na logica do negocio");
    }

    public NegocioException(String msg){
        super(msg);
    }
}
