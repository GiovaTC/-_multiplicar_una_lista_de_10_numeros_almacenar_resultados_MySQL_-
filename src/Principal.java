import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        NumeroDAO dao = new NumeroDAO();

        List<Double> lista = new ArrayList<>();

        System.out.println("INGRESE 10 NUMEROS");

        for(int i=1; i<=10;i++) {
            System.out.print("Numero " + i + ": ");

            lista.add(teclado.nextDouble());
        }

        System.out.println();
        System.out.println("===== RESULTADOS =====");

        for(Double numero : lista) {
            double resultado = numero * 2;

            System.out.println(numero + " x 2 = " + resultado);

            dao.guardar(
                    new Numero(numero, resultado)
            );
        }

        dao.mostrarDatos();
        teclado.close();
    }
}