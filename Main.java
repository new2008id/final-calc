import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Input: ");
        String expression = in.nextLine();
        System.out.println("Output: " + parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int a;
        int b;
        String operation;
        String result;
        boolean isRomanNumber;
        String[] operands = expression.split("[+\\-*/]");

        if (operands.length != 2) throw new Exception("Должно быть 2 операнда");
        operation = detectOperation(expression);

        if (operation == null) throw new Exception("Неподдерживаемая математическая операция");

        // if two numbers is roman
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            // convert two numbers is roman of calculate
            a = Roman.convertToArabian(operands[0]);
            b = Roman.convertToArabian(operands[1]);
            isRomanNumber = true;
        }

        // if two numbers is arabian
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            a = Integer.parseInt(operands[0]);
            b = Integer.parseInt(operands[1]);
            isRomanNumber = false;
        }

        // if one number is roman, and two - arabian
        else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (a > 10 || b > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arabian = calc(a, b, operation);
        if (isRomanNumber) {
            // if roman number <= 0, output error
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля.");
            }
            // convert result operation arabian in roman
            result = Roman.convertToRoman(arabian);
        } else {
            // convert arabian number in type String
            result = String.valueOf(arabian);
        }
        // return result
        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String operation) {
        return switch (operation) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
            "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII",
            "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV",
            "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
            "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII",
            "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII",
            "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static boolean isRoman(String value) {
        for (int i = 0; i < romanArray.length; i++) {
            if (value.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String romanNumber) {
        for (int i = 0; i < romanArray.length; i++) {
            if (romanNumber.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }
}
