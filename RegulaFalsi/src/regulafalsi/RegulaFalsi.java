package regulafalsi;

import java.util.Scanner;

/**
 * @author Loli Delgado Lara
 */
public class RegulaFalsi {

    private double p0, p1, tol, q0, q1, p = 0, q;
    private int max_ite, i;
    private boolean flag = false;

    /**
     * Como datos de entrada se tienen las aproximaciones iniciales
     *
     * @param p0 aproximacion inicial izquierda
     * @param p1 aproximacion inicial derecha
     * @param tol tolerancia
     * @param max_ite numero maximo de iteraciones
     */
    public void capturar_datos() {
        Scanner s = new Scanner(System.in);
        System.out.println("Valor de p0:");
        p0 = s.nextDouble();
        System.out.println("Valor de p1:");
        p1 = s.nextDouble();
        System.out.println("Valor de tolerancia:");
        tol = s.nextDouble();
        System.out.println("Número de iteraciones:");
        max_ite = s.nextInt();
        System.out.println("\n-----------------------------");
        //llamada al metodo aplicado
        regula_falsi();
    }

    /**
     * Encuentra una solucion a f(x)=0 dada la funcion continua f en el
     * intervalo [p0,p1] donde f(p0) y f(p1) tienen signos opuestos
     *
     * @return p la raiz aproximada de la funcion definida
     */
    public void regula_falsi() {

        //paso 1
        i = 2;
        q0 = functio(p0);
        q1 = functio(p1);
        //System.out.println("q0 = " + q0 + " y q1 = " + q1);
        //paso 2
        while (i <= max_ite) {
            //paso 3
            p = p1 - ((q1 * (p1 - p0)) / (q1 - q0));
            //paso 4
            if (Math.abs(p - p1) < tol) {
                flag = true; //procedimiento terminado satisfactoriamente
                break;
            }
            //paso 5
            i++;
            q = functio(p);
            //paso 6
            if ((q * q1) < 0) {
                p0 = p1;
                q0 = q1;
            }
            p1 = p;
            q1 = q;
        }
        if (flag) {//con exito
            System.out.println(p + " es una raiz en " + i + " iteraciones");
        } else { //sin exito
            System.err.println("El metodo falló después de " + max_ite + " iteraciones");
        }
    }

    /**
     * Funcion que obtiene la raiz de 2
     *
     * @param value el valor que se toma al evaluar los puntos que se cree estan
     * cercanos a la raiz
     * @return la raiz
     */
    private double functio(double value) {//funcion que aproxima la raiz de 2
        return (value * value) - 3;
    }

    /**
     * Metodo que crea la instancia de la clase y comienza el funcionamiento de
     * esta
     */
    public static void main(String[] args) {
        RegulaFalsi rf = new RegulaFalsi();
        System.out.println("\tMetodo de la regla falsa o posicion falsa\n");
        rf.capturar_datos();
    }
}