import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Exception;



public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int number1, number2;
    static char operation;
    static int result;

    public static void main (String[] args) throws Exception{
        System.out.println("Введите выражение и нажмите Enter ");
//      Считываем строку userInput которую ввёл пользователь2
        String userInput = scanner.nextLine();
//      Создаём пустой символьный массив длиной 10 символов:  under_char
        char[] under_char = new char[10];
        int cnt = 0;
//      Заполняем символьный массив символами строки которую ввел пользователь и по ходу ловим знак операции
        for (int i = 0; i < userInput.length(); i++) {
            under_char[i] = userInput.charAt(i);

            if(under_char[i] == '.' || under_char[i] == ',') {
                throw new Exception("Калькулятор может принимать на вход только целые числа");
            }

            if (under_char[i] == '+') {
                operation = '+';
            }

            if (under_char[i] == '+') {
                operation = '+';
            }
            if (under_char[i] == '-') {
                operation = '-';
            }
            if (under_char[i] == '*') {
                operation = '*';
            }
            if (under_char[i] == '/') {
                operation = '/';
            }
        }


        String under_charString = String.valueOf(under_char);
        String[] blacks = under_charString.split("[+-/*]");

        if (operation==0 || blacks.length != 2)
        {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        String stable00 = blacks[0];
        String stable01 = blacks[1];
        String string03 = stable01.trim();


        for (int i = 0; i < blacks[1].length(); i++) {

            if (blacks[1].charAt(i) == '\0') {
                cnt++;
            }
        }

        if (cnt == blacks[1].length()) {
            blacks[1]="";
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if (stable00.matches("[0-9]+") && string03.matches("[0-9]+")) {
            number1 = Integer.parseInt(stable00);
            number2 = Integer.parseInt(string03);


            validateArabicNumbers(number1,number2);
            System.out.println("Результат для арабских цифр");

            result = calculated(number1, number2, operation);
            System.out.println(number1 + " " + operation + " " + number2 + " = " + result);
        }
        else
        {
            number1 = romanToNumber(stable00);
            number2 = romanToNumber(string03);

            System.out.println(number1+"    " + number2);

            validateArabicNumbers(number1,number2);

            System.out.println("Результат для римских цифр");
            result = calculated(number1, number2, operation);

            if (result < 1)
            {
                throw new Exception("В римской системе нет нуля и отрицательных чисел");
            }

            else
            {
                String resultRoman = convertNumToRoman(result);
                System.out.println(stable00 + " " + operation + " " + string03 + " = " + resultRoman);
            }
        }
    }
    private static String convertNumToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }

    private static int romanToNumber (String roman) throws Exception{
        try {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }

            else if (roman=="") {
                throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            else if (roman.matches("[0-9]")) {
                throw new Exception("Используются одновременно разные системы счисления");
            }

            else {
                throw new Exception("Калькулятор может принимать на вход числа от 1 до 10 включительно, не более");
            }

        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        //return -1;
    }

    public static int calculated (int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception: " + e);
                    System.out.println("Only integer non-zero parameters allowed");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }

    public static void validateArabicNumbers(int x, int y) throws Exception {
        if(x <= 0 || x > 10 || y <= 0 || y > 10 ) {
            throw new Exception("Калькулятор может принимать на вход числа от 1 до 10 включительно, не более");
        }
    }


}