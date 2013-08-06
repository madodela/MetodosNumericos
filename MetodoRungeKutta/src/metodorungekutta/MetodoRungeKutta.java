package metodorungekutta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Loli
 */
public class MetodoRungeKutta {

    private double h, a, b, alpha;
    private int N;
    private static ArrayList<Point> puntos;

    public MetodoRungeKutta(double a, double b, double alpha, int N) {
        this.a = a;
        this.b = b;
        this.alpha = alpha;
        this.N = N;
        puntos = new ArrayList<>();
    }

    private double f(double t, double w) {
        double Dy;
        Dy = w - (t * t) + 1;
        return Dy;
    }
    //la solucion de la ecuacion diferencial es:
    //y(t)=(t+1)^2-0.5e(t)

    public double f(Object dato) {
        double t = Double.parseDouble(String.valueOf(dato));
        return Math.pow((t + 1), 2) - 0.5 * Math.exp(t);
    }

    public void RKMethod() {
        double t, w, K1, K2, K3, K4;
        h = (b - a) / N;
        t = a;
        w = alpha;
        puntos.add(new Point(t, w));
        for (int i = 1; i <= N; i++) {
            K1 = h * f(t, w);
            K2 = h * f(t + (h / 2), w + (K1 / 2));
            K3 = h * f(t + (h / 2), w + (K2 / 2));
            K4 = h * f(t + h, w + K3);
            w = w + (K1 + 2 * K2 + 2 * K3 + K4) / 6;
            t = a + i * h;
            puntos.add(new Point(t, w));
        }
    }

    public static void main(String[] args) {

        System.out.println("\t\t\t\tM É T O D O   D E   R U N G E - K U T T A"
                + "\n\t\tP A R A   R E S O L V E R   E C U A C I O N E S   D I F E R E N C I A L E S");
        System.out.print("Número de iteraciones: ");
        int iteraciones = new Scanner(System.in).nextInt();
        MetodoRungeKutta ecuacion = new MetodoRungeKutta(0, 2, 0.5, iteraciones);
        ecuacion.RKMethod();
        System.out.println("Ecuacion diferencial: ");
        System.out.println("y(t)=(t+1)^2-0.5e(t)" + "\t0<=t<=2" + "\t\ty(0)=0.5"
                + "\n\nPolinomios de grado 1, 2 y 3 con mínimos cuadrados según (ti,wi)");
        MetodoMinimosCuadrados p1 = new MetodoMinimosCuadrados(iteraciones, 2, puntos);
        MetodoMinimosCuadrados p2 = new MetodoMinimosCuadrados(iteraciones, 3, puntos);
        MetodoMinimosCuadrados p3 = new MetodoMinimosCuadrados(iteraciones, 4, puntos);
        p1.ejecutarMinimos();
        p2.ejecutarMinimos();
        p3.ejecutarMinimos();
        System.out.println("\tPolinomio primer grado:\n\t\ty = " + p1.ecuacion
                + "\n\tPolinomio segundo grado:\n\t\ty = " + p2.ecuacion
                + "\n\tPolinomio tercer grado:\n\t\ty = " + p3.ecuacion + "\n\nT A B U L A C I Ó N:");

        System.out.println("_________________________________________________________"
                + "_________________________________________________________________\n"
                + "ti\t       Runge-Kutta\t      Valores exactos\t       P1(ti)\t    "
                + "   P2(ti)\t       P3(ti)\t         Error"
                + "\n\t\t    wi\t\t\t    y(ti)\t\t\t\t\t\t\t      | yi - wi |\n"
                + "--------------------------------------------------------------"
                + "------------------------------------------------------------");
        DecimalFormat formatot = new DecimalFormat("0.0");
        DecimalFormat formatow = new DecimalFormat("0.0000000");
        DecimalFormat formatop = new DecimalFormat("0.00000");
        double sumap1 = 0, sumap2 = 0, sumap3 = 0, yi;
        for (int i = 0; i < iteraciones; i++) {
            yi = ecuacion.f(puntos.get(i).x);
            System.out.println(formatot.format(puntos.get(i).x)
                    + "\t\t" + formatow.format(puntos.get(i).y)
                    + "\t\t" + formatow.format(yi)
                    + "\t\t" + formatop.format(p1.suma[i])
                    + "\t\t" + formatop.format(p2.suma[i])
                    + "\t\t" + formatop.format(p3.suma[i])
                    + "\t\t" + formatow.format(Math.abs(ecuacion.f(puntos.get(i).x)
                    - puntos.get(i).y)));
            sumap1 += Math.pow(yi - p1.suma[i], 2);
            sumap2 += Math.pow(yi - p2.suma[i], 2);
            sumap3 += Math.pow(yi - p3.suma[i], 2);
        }
        System.out.println("_______________________________________"
                + "___________________________________________________"
                + "________________________________");
        sumap1 /= iteraciones;
        sumap2 /= iteraciones;
        sumap3 /= iteraciones;
        System.out.println("Errores:\nError p1: " + sumap1
                + "\nError p2: " + sumap2
                + "\nError p3: " + sumap3);
        double min = 0;
        int minpol = 0;
        if (sumap1 < sumap2) {
            if (sumap1 < sumap3) {
                min = sumap1;
                minpol = 1;
            } else if (sumap3 < sumap2) {
                min = sumap3;
                minpol = 3;
            }
        } else {
            if (sumap2 < sumap3) {
                min = sumap2;
                minpol = 2;
            } else if (sumap3 < sumap1) {
                min = sumap3;
                minpol = 3;
            }
        }
        DecimalFormat formato = new DecimalFormat("0.0000");
        System.out.println("El mejor polinomio es el de grado " + minpol + " que es");
        switch (minpol) {
            case 1:
                System.out.println("y = " + p1.ecuacion + "\ncon porcentaje de error de " + formato.format(sumap1 * 100) + "%");
                break;
            case 2:
                System.out.println("y = " + p2.ecuacion + "\ncon porcentaje de error de " + formato.format(sumap2 * 100) + "%");
                break;
            case 3:
                System.out.println("y = " + p3.ecuacion + "\ncon porcentaje de error de " + formato.format(sumap3 * 100) + "%");
                break;
        }
    }
}