/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seccion4;

/**
 *
 * @author Loli Delgado
 */
public class PuntoFijo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Ejercicio 1 inciso a:");
        punto_fijo(0, 0.00001, 20, 1);
        System.out.println("Ejercicio 1 inciso d:");
        punto_fijo(0, 0.00001, 50, 2);
        System.out.println("Ejercicio 2 inciso a:");
        punto_fijo(0, 0.0001, 50, 3);
//        System.out.println("Ejercicio 2 inciso b:");
//        punto_fijo(0, 0.0001, 50, 3);
       // punto_fijo(1.5, 0.00001, 50, 3); del ejemplo del libro
    }

    static void punto_fijo(double p0, double TOL, int no_ite, int caso) {
        int i;
        double p = 0, p_0 = p0;
        boolean flag = false;
        i = 1;
        switch (caso) {
            case 1:
                while (i <= no_ite) {
                    p = g(p_0);
                    System.out.println("|i = " + i + "| p" + i + "=" + p);
                    if (Math.abs(p - p_0) < TOL) {
                        flag = true;
                        break;//satisfactorio
                    }
                    i++;
                    p_0 = p;
                }
                if (flag) {
                    System.out.println("La solución aproximada es: "
                            + p + " en " + i + " iteraciones");
                } else {
                    System.err.println("el metodo fracasó despues de "
                            + no_ite + " iteraciones");
                }
                break;
            case 2:
                while (i <= no_ite) {
                    p = g2(p_0);
                    System.out.println("|i = " + i + "| p" + i + "=" + p);
                    if (Math.abs(p - p_0) < TOL) {
                        flag = true;
                        break;//satisfactorio
                    }
                    i++;
                    p_0 = p;
                }
                if (flag) {
                    System.out.println("la solucion aproximada es: "
                            + p + " en " + i + " iteraciones");
                } else {
                    System.err.println("el metodo fracasó despues de "
                            + no_ite + " iteraciones");
                }
                break;
            case 3:
                while (i <= no_ite) {
                    p = g3(p_0);
                    System.out.println("|i = " + i + "| p" + i + "=" + p);
                    if (Math.abs(p - p_0) < TOL) {
                        flag = true;
                        break;//satisfactorio
                    }
                    i++;
                    p_0 = p;
                }
                if (flag) {
                    System.out.println("la solucion aproximada es: "
                            + p + " en " + i + " iteraciones");
                } else {
                    System.err.println("el metodo fracasó despues de "
                            + no_ite + " iteraciones");
                }
                break;
        }

    }

    static double g(double x) {
        double x2 = (2 - Math.exp(x) + x * x) / 3;
        return x2;
    }

    static double g2(double x) {
        return Math.pow(5, -x);
    }

    static double g3(double x) {
        //x debe ser entre 1 y 2
       return Math.sqrt(Math.pow(Math.E, x)/3); //ejercicio 2.a
       // return 0.5*Math.pow((10-Math.pow(x, 3)),.5); para ejemplo del libro
      //return Math.cos(x); para la solucion del  2.b
    }
}
