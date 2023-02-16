package ifmt.cba.negocio;

public class Calculadora {
    public double somar(double valor1, double valor2){
        return valor1 + valor2;
    }

    public double subtrair(double valor1, double valor2){
        return valor1 - valor2;
    }

    public double multiplicar(double valor1, double valor2){
        return valor1 * valor2;
    }

    public double dividir(double valor1, double valor2) throws ArithmeticException{
        try {
            return valor1 / valor2;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Divisao por zero");
        }
    }
}
