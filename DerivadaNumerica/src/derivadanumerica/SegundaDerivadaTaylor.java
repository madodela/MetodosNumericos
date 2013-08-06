package derivadanumerica;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Loli
 */
public class SegundaDerivadaTaylor {

    public static void main(String[] args) {
        int i, n;
        Double x, h2, y;
        ArrayList<Double> valores_x = new ArrayList<>();
        System.out.println("Derivada numerica con Polinomio de Taylor "
                + "n puntos conocidos donde el valor x NO este en extremos, sino que sea intermedio");

        do {
            System.out.println("Escriba el numero de puntos conocidos");
            n = MyReadingMethods.readInt();
        } while (n == 999999999);
        // Variables inicializadas para mas rapido mi ejemplo verificarlo
//         double a[][] = {
//         {1.8, 10.89365},
//         {1.9, 12.703199},
//         {2, 14.778112},
//         {2.1, 17.148957},
//         {2.2, 19.855030}
//         };
         
        double a[][] = new double[n][2];

        System.out.println("Vacíe su información");
        for (i = 0; i < n; i++) {
            do {
                System.out.print("x" + i + " = ");
                a[i][0] = MyReadingMethods.readDouble();
            } while (a[i][0] == 999999.99);
            valores_x.add(a[i][0]);
            do {
                System.out.print("f(" + a[i][0] + ") = ");
                a[i][1] = MyReadingMethods.readDouble();
            } while (a[i][0] == 999999.99);
        }

        DecimalFormat formato = new DecimalFormat("##.##");
        h2 = Double.parseDouble(formato.format(valores_x.get(1) - valores_x.get(0)));

        do {
            System.out.print("Punto en el que se desea evaluar la derivada: ");
            x = MyReadingMethods.readDouble();
        } while (x == 999999.99);
        if (valores_x.contains(x)) {
            int indice = valores_x.indexOf(x);
            if (indice < valores_x.size() - 1 && indice > 0) {
                //no esta en extremo entonces aplica la formula dada en clase que es...
                //f''(x0)=(1/h^2)[f(x0+h)+f(x0-h)-2f(x0)]
                y = (1 / Math.pow(h2, 2)) * (a[indice + 1][1] + a[indice - 1][1] - 2 * a[indice][1]);
                System.out.println("f''(" + x + ") = " + y);
            } else {
                System.out.println("Esta en extremo, no es posible seguir...");
            }
        } else {
            System.out.println("No esta en los valores conocidos");
        }
    }
}