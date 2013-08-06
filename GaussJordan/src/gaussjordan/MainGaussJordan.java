/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussjordan;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Loli
 */
public class MainGaussJordan {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        System.out.print("\tMÉTODO DE GAUSS-JORDAN"
                + "\n\nDatos del sistema de ecuaciones a resolver:"
                + "\nNúmero de variables: ");
        int variables = cin.nextInt();
        GaussJordan sistema = new GaussJordan(variables);
        sistema.metodoGaussJordan();

        System.out.println("\n\tSolucion encontrada en " + GaussJordan.getPasos() + " pasos:");
        int solucion = sistema.getTipoSolucion();
        switch (solucion) {
            case 1:
                System.out.println("\nSolución única:");
                ArrayList<Double> soluciones = sistema.getSoluciones();
                for (int i = 0; i < soluciones.size(); i++) {
                    System.out.println("x" + (i + 1) + " = " + soluciones.get(i));
                }
                break;
            case 2:
                ArrayList<String> sol = sistema.getInfinitas_soluciones();
                System.out.println("\nTiene soluciones infinitas:\n\n\tEcuaciones:");
                System.out.println(sol.get(0));
                break;
            case 3:
                System.out.println("\nEl sistema no tiene solución");
                break;
        }
    }
}
