package gaussjordan;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Loli
 */
public class GaussJordan {

    private double matriz[][];
    private int n, m;// n = #variables, m = #ecuaciones
    private ArrayList<Double> soluciones;
    private ArrayList<String> infinitas_soluciones;
    private static int pasos;//pasos en los que se encuentre la solucion
    /*
     1. solucion única
     2. infinitas soluciones
     3. no solucion */
    private int tipoSolucion = 1;
    int renglonCero;

    public static int getPasos() {
        return pasos;
    }

    public double[][] getMatriz() {
        return matriz;
    }

    public int getTipoSolucion() {
        return tipoSolucion;
    }

    public ArrayList<Double> getSoluciones() {
        return soluciones;
    }

    public ArrayList<String> getInfinitas_soluciones() {
        return infinitas_soluciones;
    }

    public GaussJordan(int n) {
        this.n = n;
        m = n + 1;
        matriz = new double[n][m];
        soluciones = new ArrayList<>();
        pasos = 0;
        infinitas_soluciones = new ArrayList<>();
    }

    public void metodoGaussJordan() {
        double pivote, cero;
        capturarMatriz();
        for (int i = 0; i < n; i++) {
            pivote = matriz[i][i];
            if (pivote != 0) {
                for (int j = 0; j < m; j++) {
                    matriz[i][j] = matriz[i][j] / pivote;
                    System.out.println("\npaso " + pasos);
                    pasos++;
                    imprimirMatriz();
                }
                for (int k = 0; k < n; k++) {
                    if (k != i) {
                        cero = matriz[k][i];
                        for (int j = 0; j < m; j++) {
                            matriz[k][j] = matriz[k][j] - cero * matriz[i][j];
                            System.out.println("\npaso " + pasos);
                            pasos++;
                            imprimirMatriz();
                        }
                    }
                }
            } else {
                if (matriz[i][m - 1] != 0) {//no tiene solucion
                    tipoSolucion = 3;
                } else {
                    renglonCero = i;
                    int ecu = m;
                    ecu--;
                    if (ecu - 1 < n) {//soluciones infinitas
                        tipoSolucion = 2;
                    } else if (m == n) {//solución única
                        tipoSolucion = 1;
                    }
                }
            }
        }
        if (tipoSolucion == 1) {//agrega las soluciones encontradas a un Array
            for (int i = 0; i < n; i++) {
                soluciones.add(matriz[i][m - 1]);
            }
        }
        if (tipoSolucion == 2) {
            int ecu = m - 1;
            String ecuacion = "";
            for (int a = 0; a < ecu; a++) {
                if (a != renglonCero) {
                    for (int b = 0; b < n; b++) {
                        if (b == n - 1) {//si es el ultimo coeficiente de la ecuacion
                            ecuacion += "(" + String.valueOf(matriz[a][b]) + ")"
                                    + "x" + String.valueOf(b + 1) + " = "
                                    + String.valueOf(matriz[a][n]) + "\n";
                        } else {
                            ecuacion += "(" + String.valueOf(matriz[a][b]) + ")"
                                    + "x" + String.valueOf(b + 1) + " + ";
                        }
                    }
                }
            }
            infinitas_soluciones.add(ecuacion);
        }
    }

    private void capturarMatriz() {
        Scanner cin = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == n) {
                    System.out.print("\tValor de b" + (i + 1) + ": ");
                    matriz[i][j] = cin.nextInt();
                } else {
                    System.out.print("Valor de a" + (i + 1) + "" + (j + 1) + ": ");
                    matriz[i][j] = cin.nextInt();
                }
            }
        }
    }

    private void imprimirMatriz() {
        for (int i = 0; i < n; i++) {
            System.out.print("[");
            for (int j = 0; j < m; j++) {
                if (j == n) {
                    System.out.printf("|  %f  ", matriz[i][j]);
                } else {
                    System.out.printf("  %f  ", matriz[i][j]);
                }
            }
            System.out.println("]");
        }
    }
}
