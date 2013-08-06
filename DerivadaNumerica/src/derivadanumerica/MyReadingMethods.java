package derivadanumerica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

/**
 * @author Loli
 */
public final class MyReadingMethods {
    //para leer un double

    public static double readDouble() {
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

    //para  leer un entero
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
            num = 999999999;
        }
        return num;
    }
}
