package ifmt.cba.execusao;

import ifmt.cba.negocio.Calculadora;

public class AppCalculadora {
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        System.out.println("----------------------");
        System.out.println("Somar 5 + 5: " + calc.somar(5, 5));
        System.out.println("Subtrair 5 - 3: " + calc.subtrair(5, 3));
        System.out.println("Multiplicar 5 * 5: " + calc.multiplicar(5, 5));
        System.out.println("Dividir 20 / 5: " + calc.dividir(20, 5));
        System.out.println("----------------------");
    }
}
