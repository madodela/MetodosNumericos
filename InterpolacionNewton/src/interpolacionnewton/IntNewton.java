package interpolacionnewton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

/**
 * @author Loli
 */
public class IntNewton {

    private double tabla_newton[][];
    private Double valores_x[];
    private Double valores_x_originales[];
    private double valores_fx[];
    private static int puntos_conocidos = 0;
    private Double coef_b[];
    private double x;

//*_______________________________Constructor________________________________*//
    public IntNewton() {

        tabla_newton = new double[puntos_conocidos][puntos_conocidos];
        valores_x = new Double[puntos_conocidos];
        valores_x_originales = new Double[puntos_conocidos];
        valores_fx = new double[puntos_conocidos];
        coef_b = new Double[puntos_conocidos];
    }

//*______________________________Captura de valores__________________________*//
    public void capturarValores() {
        System.out.println("Valores conocidos:");
        for (int i = 0; i < puntos_conocidos; i++) {
            do {
                System.out.print("x" + i + " = ");
                valores_x[i] = readDouble();
                valores_x_originales[i] = valores_x[i];
            } while (valores_x[i] == 999999.99);//validaciones para tipo de dato
            do {
                System.out.print("f(" + valores_x[i] + ") = ");
                tabla_newton[i][0] = readDouble();
            } while (tabla_newton[i][0] == 999999.99);//validacion tipo de dato
            valores_fx[i] = tabla_newton[i][0];
        }

        ordenarSegunFx();
        creacionEvaluacionPolinomio();
    }

//*___________ordenacion tabla newton por burbuja mejorado___________________*//
    private void ordenarSegunFx() {
        boolean sw = true;
        int li = 0;
        do {
            li++;
            sw = true;
            for (int i = 0; i < puntos_conocidos - li; i++) {
                if (valores_fx[i] < valores_fx[i + 1]) { // compara los valores
                    // intercambia los datos
                    double tem = valores_fx[i];
                    double tem2 = valores_x[i];
                    valores_fx[i] = valores_fx[i + 1];
                    valores_x[i] = valores_x[i + 1];
                    valores_fx[i + 1] = tem;
                    valores_x[i + 1] = tem2;
                    sw = false;
                }
            }
        } while (!sw);
        for (int i = 0; i < puntos_conocidos; i++) {
            tabla_newton[i][0] = valores_fx[i];
        }
        tablaDeDiferenciasDeNewton();//creacion de la tabla de diferencias
    }

//*__________________---------___________----------__________-------_________*//
    private void tablaDeDiferenciasDeNewton() {
        int b = 0, i, j, k;
        coef_b[b] = tabla_newton[0][0];
        b++;
        for (k = 1; k < puntos_conocidos; k++) {
            for (i = k; i < puntos_conocidos; i++) {
                tabla_newton[i][k] = (tabla_newton[i][k - 1]
                        - tabla_newton[i - 1][k - 1])
                        / (valores_x[i] - valores_x[i - k]);
            }
            coef_b[b++] = tabla_newton[k][k];// los coeficientes del 
            //polinomio son la diagonal principal de la matriz
        }
        //como deben ser invertidos creo un arreglo auxiliar    
        double[] otro_coef = new double[puntos_conocidos + 1];
        j = 0;
        for (i = puntos_conocidos - 1; i >= 0; i--) {
            otro_coef[j] = coef_b[i];
            j++;
        }
        i = 0;
        do {
            coef_b[i] = otro_coef[i];
            i++;
        } while (i < puntos_conocidos);
        otro_coef = null;//lo elimino porque ya no lo ocupo y solo gasta memoria
        imprimirTabla();
    }

//**_________________________Imprimir tabla de newton______________________**//
    private void imprimirTabla() {
        int i, j;
        for (i = 0; i < puntos_conocidos; i++) {
            System.out.printf("%.2f                ", valores_x[i]);
            for (j = 0; j < puntos_conocidos; j++) {
                System.out.printf("%.2f                ", tabla_newton[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
        for (i = 0; i < coef_b.length; i++) {
            System.out.printf("%.2f       ", coef_b[i]);
        }
    }

//**_________________________creacion polinomio newton______________________**//
    private void creacionEvaluacionPolinomio() {
        do {
            System.out.print("Valor a interpolar: ");
            x = readDouble();
        } while (x == 999999.99);//validacion de tipo de dato
        double valor = 0;
        String polinomio = "P(x) = ";
        switch (puntos_conocidos) {
            case 4:
                valor = coef_b[3]
                        + coef_b[2] * (x - valores_x_originales[1])
                        + coef_b[1] * (x - valores_x_originales[1]) * (x - valores_x_originales[2])
                        + coef_b[0] * (x - valores_x_originales[1]) * (x - valores_x_originales[2])
                        * (x - valores_x_originales[3]);
                polinomio += coef_b[3].toString() + " "
                        + coef_b[2].toString() + "(x - " + valores_x_originales[1].toString() + ") + "
                        + coef_b[1].toString() + "(x - " + valores_x_originales[1].toString() + ")"
                        + "(x - " + valores_x_originales[2].toString() + ") + "
                        + coef_b[0].toString() + "(x - " + valores_x_originales[1].toString() + ")"
                        + "(x - " + valores_x_originales[2].toString() + ")(x - " + valores_x_originales[3].toString() + ")";
                System.out.println("El polinomio formado es:\n" + polinomio);
                System.out.println("Evaluado en x = " + x + " su valor es: \n P(" + x + ") = " + valor);
                break;
            case 5:
                valor = coef_b[4]
                        + coef_b[3] * (x - valores_x_originales[1])
                        + coef_b[2] * (x - valores_x_originales[1]) * (x - valores_x_originales[2])
                        + coef_b[1] * (x - valores_x_originales[1]) * (x - valores_x_originales[2]) * (x - valores_x_originales[3])
                        + coef_b[0] * (x - valores_x_originales[1]) * (x - valores_x_originales[2]) * (x - valores_x_originales[3]) * (x - valores_x_originales[4]);
                polinomio += coef_b[4].toString() + " + "
                        + coef_b[3].toString() + "(x - " + valores_x_originales[1].toString() + ") + "
                        + coef_b[2].toString() + "(x - " + valores_x_originales[1].toString() + ")(x - " + valores_x_originales[2].toString() + ") + "
                        + coef_b[1].toString() + "(x - " + valores_x_originales[1].toString() + ")(x - " + valores_x_originales[2].toString() + ")(x - " + valores_x_originales[3].toString() + ") + "
                        + coef_b[0].toString() + "(x - " + valores_x_originales[1].toString() + ")(x - " + valores_x_originales[2].toString() + ")(x - " + valores_x_originales[3].toString() + ")(x - " + valores_x_originales[4].toString() + ")";
                System.out.println("El polinomio formado es:\n" + polinomio);
                System.out.println("Evaluado en x = " + x + " su valor es: \n P(" + x + ") = " + valor);
                break;
        }
    }

//**************************funciones estáticas*******************************//
    
//**_________________________funcion principal______________________**//
    public static void main(String[] args) {
        int puntos;
        do {
            System.out.println("\n\t\tALGORITMO DE INTERPOLACION DE NEWTON "
                    + "PARA 4 o 5 PUNTOS CONOCIDOS");
            System.out.print("Numero de puntos conocidos: ");
            puntos = readInt();
        } while (puntos == 999999999 || puntos < 4 || puntos > 5);
        puntos_conocidos = puntos;
        IntNewton problema = new IntNewton();
        problema.capturarValores();
    }

//**_________________________leer un entero y validarlo_____________________**//
    public static int readInt() {
        int num;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String sdato;
            sdato = br.readLine();
            num = Integer.parseInt(sdato);
        } catch (InputMismatchException | IOException |
                NumberFormatException ske) { //multicatch Exception :)
            // System.err.println("Wrong data type\n\n");
            num = 999999999;
        }
        return num;
    }

//**_________________________leer un real y validarlo_____________________**//
    public double readDouble() {
        double num;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String sdato;
            sdato = br.readLine();
            num = Double.parseDouble(sdato);
        } catch (InputMismatchException | IOException |
                NumberFormatException ske) {
            num = 999999.99;
        }
        return num;
    }
}