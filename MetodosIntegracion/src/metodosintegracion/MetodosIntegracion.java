package metodosintegracion;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Loli
 */
public class MetodosIntegracion {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("-----------------------------------------------------"
                + "\n\tIntegral mediante rectangulos o trapecios");
        System.out.println("Puntos que acotan el area a integrar:");
        System.out.print("a: ");
        double a = s.nextDouble();
        System.out.print("b: ");
        double b = s.nextDouble();
        System.out.print("Cantidad de subintervalos:\nn = ");
        int n = s.nextInt();
        System.out.println("\tResultado de integral = " + metodoDeLosRectangulos(a, b, n));
        System.out.println("-----------------------------------------------------"
                + "\n\tPor porcentaje de puntos bajo la curva"
                + " con un millon de \"lanzamientos\"");
        System.out.println("\tResultado de la integral = " + metodoDeMontecarlo(a, b, 1000000));
    }

    private static double f(double x) {
        return 1 / (16 + Math.pow(x, 2));
    }

    public static double metodoDeLosRectangulos(double a, double b, int N) {
        double h, x, area_resultante;
        ArrayList<Double> y = new ArrayList<>();
        h = (b - a) / (N - 1); //base
        x = a;
        y.add(f(x));
        x += h;
        while (x < b) {
            y.add(f(x));
            x += h;
        }
        area_resultante = y.get(0) + y.get(N - 1);
        for (int i = 2; i <= N - 1; i++) {
            area_resultante += (2 * y.get(i - 1));
        }
        area_resultante *= (h / 2);
        return area_resultante;
    }

    public static double metodoDeMontecarlo(double a, double b, int N) {
        double area_resultante, R1, R2, C = b;
        int n = 0;
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            R1 = r.nextDouble() * (b - a);
            R2 = r.nextDouble() * C;
            if (R2 <= f(R1)) {
                n++;
            }
        }
        area_resultante = ((b - a) * C * n) / N;
        return area_resultante;
    }
}
