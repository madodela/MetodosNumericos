package Lagrange;

import java.io.*;

/**
 * @author Loli Delgado
 */
public class Lagrange {

    public static void MetodoInterLagrange() {
        int n, i;
        double x, y;

        System.out.println("\t\t\t\"METODO DE INTERPOLACION LAGRANGE\n\t\t\t\t(2-4 PUNTOS)\"");
        do {
            System.out.println("Escriba el numero de puntos conocidos");
            n = readint();
        } while (n < 2 || n > 4);
        double a[][] = new double[n][2];
        System.out.println("Vacie su información");
        for (i = 0; i < n; i++) {
            System.out.print("x" + i + " = ");
            a[i][0] = read();
            System.out.print("f(" + a[i][0] + ") = ");
            a[i][1] = read();
        }
        System.out.println("¿Cuál es el valor a interpolar?");
        x = read();
        //Imprimir tabla con puntos conocidos
//        for (i = 0; i < n; i++) {
//            for (int j = 0; j < 2; j++) {
//                 System.out.printf("%.4f  ", a[i][j]);
//            }
//            System.out.println("");
//        }
        switch (n) {
            case 2:
                y = (((((x - a[1][0]) * a[0][1]) / (a[0][0] - a[1][0])) + (((x - a[0][0])
                        * a[1][1]) / (a[1][0] - a[0][0]))));
                System.out.println("f(" + x + ") en ese punto es: " + y);
                break;
            case 3:
                y = ((x - a[1][0]) * (x - a[2][0]) * a[0][1]) / ((a[0][0] - a[1][0])
                        * (a[0][0] - a[2][0])) + ((x - a[0][0]) * (x - a[2][0]) * a[1][1])
                        / ((a[1][0] - a[0][0]) * (a[1][0] - a[2][0])) + ((x - a[1][0])
                        * ((x - a[0][0]) * a[2][1]) / ((a[2][0] - a[0][0]) * (a[2][0] - a[1][0])));
                System.out.println("f(" + x + ") en ese punto es: " + y);
                break;
            case 4:
                y = ((x - a[1][0]) * (x - a[3][0]) * (x - a[2][0]) * a[0][1])
                        / ((a[0][0] - a[1][0]) * (a[0][0] - a[2][0]) * (a[0][0] - a[3][0]))
                        + ((x - a[0][0]) * (x - a[2][0]) * (x - a[3][0]) * a[1][1])
                        / ((a[1][0] - a[0][0]) * (a[1][0] - a[2][0]) * (a[1][0] - a[3][0]))
                        + ((x - a[0][0]) * (x - a[1][0]) * ((x - a[3][0]) * a[2][1]) / ((a[2][0]
                        - a[0][0]) * (a[2][0] - a[1][0]) * (a[2][0] - a[3][0])) + ((x - a[0][0])
                        * (x - a[1][0]) * ((x - a[2][0]) * a[3][1]) / ((a[3][0] - a[0][0])
                        * (a[3][0] - a[1][0]) * (a[3][0] - a[2][0]))));
                System.out.println("f(" + x + ") en ese punto es: " + y);
                break;
            default:
                System.out.println("INVALIDO");
                break;
        }
    }

    public static double read() {
        double num;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String sdato;
            sdato = br.readLine();
            num = Double.parseDouble(sdato);
        } catch (IOException ioe) {
            num = 0.0;
        }
        return num;
    }

    //para  leer un entero
    public static int readint() {
        int num;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String sdato;
            sdato = br.readLine();
            num = Integer.parseInt(sdato);
        } catch (IOException ioe) {
            num = 0;
        }
        return num;
    }

    public static void main(String[] args) {
        MetodoInterLagrange();
    }
}
