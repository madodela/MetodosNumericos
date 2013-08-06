package euler;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Loli
 */
public class Euler {

    private double h, a, b, N, alfa;
    private int derivada;
    private ArrayList puntosT;
    private DecimalFormat formatoT, formatoW;

    public ArrayList getPuntosT() {
        return puntosT;
    }

    public Euler(int derivada, double a, double b, double N, double alfa) {
        this.a = a;
        this.b = b;
        this.N = N;
        this.alfa = alfa;
        this.derivada = derivada;
        formatoT = new DecimalFormat("0.0");
        formatoW = new DecimalFormat("0.0000000");
        puntosT = new ArrayList<>();
        EulerMethod();
    }

    public static void main(String[] args) {
        // Captura de datos manualmente
        System.out.print("Cota inferior (a): ");
        double a = MyReadingMethods.readDouble();
        System.out.print("Cota superior (b): ");
        double b = MyReadingMethods.readDouble();
        System.out.print("Condicion inicial alfa: ");
        double alfa = MyReadingMethods.readDouble();
        System.out.print("Entero N: ");
        int N = MyReadingMethods.readInt();
        System.out.println("Llegando a la primer derivada");
        Euler ejemplo = new Euler(1, a, b, N, alfa);
        System.out.println("Llegando a la segunda derivada");
        ejemplo = new Euler(2, a, b, N, alfa);
        //Ejemplo con datos predefinidos y tomados del libro de burden
//        int N=10;
//        System.out.println("Llegando a la primer derivada");
//        Euler ejemplo = new Euler(1, 0, 2, N, 0.5);
//        System.out.println("Llegando a la segunda derivada");
//        ejemplo = new Euler(2, 0, 2, N, 0.5);
        System.out.println("\tValores exactos y(t)");
        DecimalFormat formato = new DecimalFormat("0.0000000");
        for (int i = 0; i < N; i++) {
            System.out.println(formato.format(ejemplo.f(ejemplo.getPuntosT().get(i))));
        }
    }

    private void EulerMethod() {
        System.out.println("\tValores (ti,wi)");
        double t, w;
        h = (b - a) / N;
        t = a;
        puntosT.add(formatoT.format(t));
        w = alfa;
        System.out.println("(" + formatoT.format(t) + " , " + formatoW.format(w) + ")");
        for (int i = 1; i <= N; i++) {

            if (derivada == 1) {
                w = w + (h * f(t, w));
            } else {
                w = w + h * (f(t, w) + (h / 2) * Df(t, w));
            }
            t = a + i * h;
            puntosT.add(formatoT.format(t));
            System.out.println("(" + formatoT.format(t) + " , " + formatoW.format(w) + ")");
        }
    }

    private double f(double t, double w) {
        double Dy;
        Dy = w - (t * t) + 1;
        return Dy;
    }
    //la solucion de la ecuacion diferencial es:
    //y(t)=(t+1)^2-0.5e(t)
    private double f(Object dato){
        double t = Double.parseDouble(String.valueOf(dato));
        return Math.pow((t+1),2)-0.5*Math.exp(t);
    }
    private double Df(double t, double w) {
        double DDy;
        DDy = f(t, w) - (2 * t);
        return DDy;
    }
}
