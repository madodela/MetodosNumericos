/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seccion3;

/**
 *
 * @author Loli Delgado
 */
public class Biseccion {

    private double a, b;
    private int n;

    public Biseccion(double a, double b, int n, int caso) {
        this.a = a;
        this.b = b;
        this.n = n;
        metodo(caso);
    }

    private void metodo(int tarea) {
        int i = 1;
        double p=0;
        switch (tarea) {
            case 1:

                while (i < n) { //mientras i<n
                    System.out.println("--------" + i + "--------");
                    p = (a + b) / 2;
                    System.out.println("p" + i + "=" + p + "\na= " + a + "\nb=" + b);
                    System.out.println("f(a)=" + f1(a) + "\nf(b)=" + f1(p));
                    if (f1(a) * f1(p) < 0) {    //	si f(a)f(p)<0 entonces
                        b = p;
                    } else {
                        a = p;
                    }
                    //	fin si
                    i = i + 1;
                }//fin mientras
                break;
            case 2:

                while (i < n) { //mientras i<n
                    System.out.println("--------" + i + "--------");
                    p = (a + b) / 2;
                    System.out.println("p" + i + "=" + p + "\na= " + a + "\nb=" + b);
                    System.out.println("f(a)=" + f2(a) + "\nf(b)=" + f2(p));
                    if (f2(a) * f2(p) < 0) {    //	si f(a)f(p)<0 entonces
                        b = p;
                    } else {
                        a = p;
                    }
                    //	fin si
                    i = i + 1;
                }//fin mientras

                break;
        }
        System.out.println("\t\tPor lo tanto p3 = " + p);
    }

    private double f1(double x) {
        return Math.sqrt(x) - Math.cos(x);
    }

    private double f2(double x) {
        return 3 * (x + 1) * (x - .5) * (x - 1);
    }
}
