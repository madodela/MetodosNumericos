package muldiv;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Loli Delgado Lara
 */
public class MulDiv {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int opc = 0;
        do {
            try {
                System.out.println("\n\tEscoga la operación que desea realizar"
                        + "\n1. Dividir"
                        + "\n2. Multiplicar"
                        + "\n3. Salir");
                opc = s.nextInt();
            } catch (InputMismatchException d) {
                opc = 0;
            }
            switch (opc) {
                case 1:
                    System.out.println("\nDame un divisor grandote");
                    BigInteger divisor = s.nextBigInteger();
                    System.out.println("Dame un dividendo grandote");
                    BigInteger dividendo = s.nextBigInteger();
                    if (!dividendo.equals(BigInteger.ZERO)) {
                        BigInteger res_div = divisor.divide(dividendo);
                        System.out.println("El resultado es: " + res_div);
                    } else {
                        System.err.println("La division entre cero no existe");
                    }
                    break;
                case 2:
                    System.out.println("\nDame un numero grandote a multiplicar");
                    BigInteger multi = s.nextBigInteger();
                    System.out.println("Dame otro numero grandote para que lo multiplique");
                    BigInteger multi2 = s.nextBigInteger();
                    BigInteger res_mul = multi.multiply(multi2);
                    System.out.println("El resultado es: " + res_mul);
                    break;
            }
        } while (opc != 3);

    }
}
