/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seccion3;

/**
 *
 * @author Loli Delgado
 */
public class EjerciciosIII {

    public static void main(String[] args) {
        System.out.println("seccion III \n\t Ejercicio numero 1");
        Biseccion ejercicio1 = new Biseccion(0, 1, 4, 1);//parametros, a, b, n, num de ejercicio
        System.out.println("\n\t Ejercicio numero 2.a");
        Biseccion ejercicio2a = new Biseccion(-2, 1.5, 4, 2);//a, b, n, num de ejercicio
        System.out.println("\n\t Ejercicio numero 2.b");
        Biseccion ejercicio2b = new Biseccion(-1.25, 2.5, 4, 2);//a, b, n, num de ejercicio
    }
}
