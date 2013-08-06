package derivadanumerica;

/**
 * @author Loli Delgado
 */
public class DerivadaNumericaLagrange {

    public static void main(String[] args) {

        int i, n;
        double x, y;
        System.out.println("Derivada numerica con Polinomio de Lagrange "
                + "CON 2 o 3 PUNTOS CONOCIDOS");
        do {
            do {
                System.out.println("Escriba el numero de puntos conocidos");
                n = MyReadingMethods.readInt();
            } while (n == 999999999);
        } while (n > 3 || n < 1);
        double a[][] = new double[n][2];

        System.out.println("Vacíe su información");
        for (i = 0; i < n; i++) {
            do {
                System.out.print("x" + i + " = ");
                a[i][0] = MyReadingMethods.readDouble();
            } while (a[i][0] == 999999.99);
            do {
                System.out.print("f(" + a[i][0] + ") = ");
                a[i][1] = MyReadingMethods.readDouble();
            } while (a[i][0] == 999999.99);
        }
        //Imprimir tabla con puntos conocidos
//        for (i = 0; i < n; i++) {
//            for (int j = 0; j < 2; j++) {
//                 System.out.printf("%.4f  ", a[i][j]);
//            }
//            System.out.println("");
//        }
        do {
            System.out.print("Punto en el que se desea evaluar la derivada: ");
            x = MyReadingMethods.readDouble();
        } while (x == 999999.99);
        //segun puntos conocidos 2 ó 3 puntos conocidos solamente según mis notas
        switch (n) {
            case 2:
                //L0'(x)f(x0)+L1'(x)f(x1) ; L0'(x) = 1/(x0-x1) ;  L1'(x) = 1/(x1-x0)
                y = (a[0][1] * (1 / (a[0][0] - a[1][0]))) + (a[1][1] * (1 / (a[1][0] - a[0][0])));
                System.out.println("f'( " + x + ") = " + y);
                break;
            case 3:
                //f(x0)L0'(x)+f(x1)L1'(x)+f(x2)L2'(x); L0'(x) = (2x-x2-x1)/(x0-x1)(x0-x2) 
                // L1'(x) = (2x-x0-x2)/(x1-x0)(x1-x2) ; L2'(x) = (2x-x0-x1)/(x2-x0)(x2-x1)
                y = (a[0][1] * ((2 * x) - a[2][0] - a[1][0])) / ((a[0][0] - a[1][0]) * (a[0][0] - a[2][0]))
                        + (a[1][1] * ((2 * x) - a[0][0] - a[2][0])) / ((a[1][0] - a[0][0]) * (a[1][0] - a[2][0]))
                        + (a[2][1] * ((2 * x) - a[0][0] - a[1][0])) / ((a[2][0] - a[0][0]) * (a[2][0] - a[1][0]));
                System.out.println("f'( " + x + ") = " + y);
                break;
        }
    }
}
