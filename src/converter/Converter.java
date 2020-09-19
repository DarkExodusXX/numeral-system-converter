package converter;

public class Converter {
    static final char[] DICT = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    static final String DICT_TO_STRING = String.copyValueOf(DICT);

    static int longToDecimal(String number, int base) {
        int newNumber = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            newNumber += DICT_TO_STRING.indexOf(number.charAt(i)) * Math.pow(base, number.length() - 1 - i);
        }
        return newNumber;
    }

    static double doubleToDecimal(String number, int base) {
        String integerPart = number.split("\\.")[0];
        String fractionalPart = number.split("\\.")[1];
        int integerResult = longToDecimal(integerPart, base);
        double fractionalResult = 0;
        for (int i = 0; i < fractionalPart.length(); i++) {
            fractionalResult += DICT_TO_STRING.indexOf(fractionalPart.charAt(i)) * Math.pow(base, -i - 1);
        };
        return integerResult + fractionalResult;
    }

    static StringBuilder longToAnotherBase(long decimalNumber, int base) {
        StringBuilder result = new StringBuilder();
        if (base != 1) {
            while (decimalNumber != 0) {
                result.append(DICT[(int) (decimalNumber % base)]);
                decimalNumber /= base;
            }
        } else {
            for (int i = 0; i < decimalNumber; i++) {
                result.append(1);
            }
        }
        return result.reverse();
    }

    static StringBuilder doubleToAnotherBase(double decimalNumber, int base) {
        String decimalNumberToString = Double.toString(decimalNumber);
        String integerPart = decimalNumberToString.split("\\.")[0];
        String fractionalPart = decimalNumberToString.split("\\.")[1];
        StringBuilder integerResult = longToAnotherBase(Long.parseLong(integerPart), base);
        if (integerResult.length() < 1) {
            integerResult.append(0);
        }
        StringBuilder fractionalResult = new StringBuilder();
        double fractionalPartToDouble = Double.parseDouble(fractionalPart) / Math.pow(10, fractionalPart.length());
        for (int i = 0; i < 5; i++) {
            fractionalResult.append(DICT_TO_STRING.charAt((int) Math.floor(fractionalPartToDouble * base)));
            fractionalPartToDouble = fractionalPartToDouble * base - Math.floor(fractionalPartToDouble * base);
        }
        return integerResult.append(".").append(fractionalResult);
    }

    static boolean inputHasError(String number, int sourceBase) {
        String subDict;
        if (sourceBase != 36) {
            subDict = DICT_TO_STRING.substring(0, sourceBase + 1);
        } else {
            subDict = DICT_TO_STRING.substring(0, sourceBase);
        }
        for (int i = 0; i < number.length(); i++) {
            if (subDict.indexOf(number.charAt(i)) == -1 && number.charAt(i) != '.') {
                return true;
            }
        }
        return false;
    }
}
