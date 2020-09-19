package converter;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (!scanner.hasNextInt()) {
            System.out.println("Error");
            return;
        }
        int sourceBase = scanner.nextInt();
        if (sourceBase > 36 || sourceBase < 1) {
            System.out.println("Error");
            return;
        }
        if (!scanner.hasNext()) {
            System.out.println("Error");
            return;
        }
        String number = scanner.next();
        if (Converter.inputHasError(number, sourceBase)) {
            System.out.println("Error");
            return;
        }
        if (!scanner.hasNextInt()) {
            System.out.println("Error");
            return;
        }
        int base = scanner.nextInt();
        if (base > 36 || base < 1) {
            System.out.println("Error");
            return;
        }
        StringBuilder result;
        if (number.split("\\.").length > 1) {
            double decimalNumber = Converter.doubleToDecimal(number, sourceBase);
            result = Converter.doubleToAnotherBase(decimalNumber, base);
        } else {
            int decimalNumber = Converter.longToDecimal(number, sourceBase);
            result = Converter.longToAnotherBase(decimalNumber, base);
        }
        System.out.println(result);
    }
}
