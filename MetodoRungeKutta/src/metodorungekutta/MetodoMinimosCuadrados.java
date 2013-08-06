package metodorungekutta;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Loli
 */
public class MetodoMinimosCuadrados {

    int dato;
    int grado;
    double valores[][];
    double sistema[][];
    double suma[];
    String ecuacion = "";

    public MetodoMinimosCuadrados(int dato, int grado, ArrayList<Point>pares) {
        this.dato = dato;
        this.grado = grado;
        this.valores = a_to_d(pares);
        this.sistema = new double[grado + 5][grado + 5];
        this.suma = new double[dato];
    }
    private double[][] a_to_d (ArrayList<Point> puntos) {
        int tamanio = puntos.size()-1;
        double[][] puntos_d = new double[2][tamanio + 1];
        for (int i = 1; i <= tamanio; i++) {
            puntos_d[0][i] = puntos.get(i - 1).x;
            puntos_d[1][i] = puntos.get(i - 1).y;
        }
        return puntos_d;
    }

    void ejecutarMinimos() {
        acomodarSistema();
        gauss();
        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i = 1; i <= grado; i++) {
            ecuacion += " + (" + df.format(sistema[i][grado + 1]) + ")x^" + (i - 1);
        }
        for (int i = 0; i < dato; i++) {
            for (int j = 1; j <= grado; j++) {
                suma[i] += Math.pow(valores[0][i + 1], j - 1) * sistema[j][grado + 1];
            }
        }
    }

    private void acomodarSistema() {
        int i, j, k;
        double sum, termino, exponente;

        for (i = 1; i <= grado; i++) {
            for (j = 1; j <= grado; j++) {
                sum = 0;
                exponente = i + j - 2;
                for (k = 1; k <= dato; k++) {
                    termino = valores[0][k];
                    sum = sum + Math.pow(termino, exponente);
                }
                sistema[i][j] = sum;
            }
        }
        for (i = 1; i <= grado; i++) {
            sum = 0;
            exponente = i - 1;
            for (k = 1; k <= dato; k++) {
                termino = valores[0][k];
                sum = sum + valores[1][k] * Math.pow(termino, exponente);
            }
            sistema[i][grado + 1] = sum;
        }
    }

    private void gauss() {
        int no_cero, col, c1, c2, a;
        double pivote, v1;

        for (col = 1; col <= grado; col++) {
            no_cero = 0;
            a = col;
            while (no_cero == 0) {
                if (sistema[a][col] != 0) {
                    no_cero = 1;
                } else {
                    a++;
                }
            }
            pivote = sistema[a][col];
            for (c1 = 1; c1 <= (grado + 1); c1++) {
                v1 = sistema[a][c1];
                sistema[a][c1] = sistema[col][c1];
                sistema[col][c1] = v1 / pivote;
            }
            for (c2 = col + 1; c2 <= grado; c2++) {
                v1 = sistema[c2][col];
                for (c1 = col; c1 <= (grado + 1); c1++) {
                    sistema[c2][c1] = sistema[c2][c1] - v1 * sistema[col][c1];
                }
            }
        }
        for (col = grado; col >= 1; col--) {
            for (c1 = (col - 1); c1 >= 1; c1--) {
                sistema[c1][grado + 1] = sistema[c1][grado + 1] - sistema[c1][col] * sistema[col][grado + 1];
                sistema[c1][col] = 0;
            }
        }
    }
}
