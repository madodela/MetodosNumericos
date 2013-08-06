package derivadanumerica;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Loli
 */
public class PuntosEquidistantes {

    static ArrayList<Double> val_x = new ArrayList<>();
    static double[][] a;

    public static void main(String[] args) {
        int i, n;
        double x, y;
        System.out.println("Derivada numerica con  "
                + "CON N PUNTOS CONOCIDOS Y EQUIDISTANTES\n");

        do {
            System.out.println("Escriba el numero de puntos conocidos");
            n = MyReadingMethods.readInt();
        } while (n == 999999999);


        a = new double[n][2];

        System.out.println("Vacíe su información");
        for (i = 0; i < n; i++) {
            do {
                System.out.print("x" + i + " = ");
                a[i][0] = MyReadingMethods.readDouble();
            } while (a[i][0] == 999999.99);
            val_x.add(a[i][0]);
            do {
                System.out.print("    f(" + a[i][0] + ") = ");
                a[i][1] = MyReadingMethods.readDouble();
            } while (a[i][1] == 999999.99);
        }
        //se verifica que sean equidistantes
        if (son_equidistantes(n)) {
            DecimalFormat formato = new DecimalFormat("##.##");
            double h = Double.parseDouble(formato.format(val_x.get(1) - val_x.get(0)));
            do {
                System.out.print("Punto en el que se desea evaluar la derivada: ");
                x = MyReadingMethods.readDouble();
            } while (x == 999999.99);
            if (val_x.contains(x)) {
                int indice = val_x.indexOf(x);
                if (indice < val_x.size() - 1) {//   que no sea extremo de todos los que dieron

                    //por formula a) de la regla de los tres puntos que nos dio en clase
                    double fx0, fx1, fx2;
                    try {
                        regla_regresiva(indice, h, x);

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        regla_centrada(indice, h, x);
                    }
                    try {
                        regla_centrada(indice, h, x);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("No se pudo aplicar esta regla");
                    }
                } else {
                    System.out.println("Esta en extremo, no es posible seguir...");
                }
            } else {
                System.out.println("No esta en los valores conocidos");
            }
        } else {
            System.out.println("No son equidistantes");
        }
    }

    private static boolean son_equidistantes(int n) {
        DecimalFormat formato = new DecimalFormat("##.##");
        double dif1 = Double.parseDouble(formato.format(val_x.get(1) - val_x.get(0))), dif2;
        boolean equidistantes = true;
        for (int p = 1; p < val_x.size() - 1; p++) {
            int q = p + 1;
            dif2 = Double.parseDouble(formato.format(val_x.get(q) - val_x.get(p)));
            if (dif1 != dif2) {
                equidistantes = false;
                break;
            }
            dif1 = dif2;
        }
        return equidistantes;
    }

    private static void regla_regresiva(int indice, double h, double x) throws ArrayIndexOutOfBoundsException {
        System.out.println("\n\tPor formula regresiva de la regla de los 3 puntos:");
        double fx0, fx1, fx2, y;
        //fx1 = f(x0+h), fx2 = f(x0+2h)
        fx0 = a[indice][1];
        fx1 = a[indice + 1][1];
        fx2 = a[indice + 2][1];
        //ahora si utiliza la formula
        y = (1 / (2 * h)) * ((-3 * fx0) + (4 * fx1) - fx2);
        System.out.println("f'(" + x + ") = " + y);
    }

    private static void regla_centrada(int indice, double h, double x) throws ArrayIndexOutOfBoundsException {
        //la formula del inciso b del cuaderno
        double fx0, fx2, y;
        System.out.println("\n\tPor fórmula centrada de la regla de los 3 puntos");
        //fx0 = f(x0-h) y fx2 = f(x0+h)
        fx0 = a[indice - 1][1];
        fx2 = a[indice + 1][1];
        //y la formula ahora si
        y = (1 / (2 * h)) * (fx2 - fx0);
        System.out.println("f'(" + x + ") = " + y);
    }
}
