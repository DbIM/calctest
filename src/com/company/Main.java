package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        begin();
        System.out.println("Напишите выражение:");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        System.out.println(getInputString(inputString));
    }

    //основной блок
    public static String getInputString(String inputString) {
        Numbers digit1 = new Numbers();
        Numbers digit2 = new Numbers();
        String result = "";
        String[] inputArray = inputString.split(" ");

        if (inputArray.length != 3) {
            System.out.println("строка не является математической операцией или " +
                    "формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            close();
        }

        digit1.setNum(inputArray[0]);
        char sign = inputArray[1].charAt(0);
        digit2.setNum(inputArray[2]);

        if (testIsItArabic(digit1.getNum()) == 'a') {
            digit1.setArabic(true);
        }

        if (digit1.isArabic()) {
            isNumCorrect(digit1.getNum());
        }

        if (testIsItArabic(digit1.getNum()) == 'r') {
            digit1.setArabic(false);
        }

        if (!digit1.isArabic()) {
            testIsItRoman(digit1.getNum());
        }

        if (testIsItArabic(digit2.getNum()) == 'a') {
            digit2.setArabic(true);
        }

        if (digit2.isArabic()) {
            isNumCorrect(digit2.getNum());
        }

        if (testIsItArabic(digit2.getNum()) == 'r') {
            digit2.setArabic(false);
        }

        if (!digit2.isArabic()) {
            testIsItRoman(digit2.getNum());
        }

        try {
            if (digit1.isArabic() && !digit2.isArabic()) {
                throw new Exception("используются одновременно разные системы счисления");
            }
            if (digit2.isArabic() && !digit1.isArabic()) {
                throw new Exception("используются одновременно разные системы счисления");
            }
            if (!digit1.isArabic()) {
                int num1, num2;
                num1 = romanToArabic(digit1.getNum());
                num2 = romanToArabic(digit2.getNum());
                result = String.valueOf(math(num1, sign, num2));
                result = "Ответ: " + String.valueOf(arabicToRoman(Integer.parseInt(result))).toUpperCase();
            } else {
                result = "Ответ: " +  String.valueOf(math(Integer.parseInt(digit1.getNum()), sign, Integer.parseInt(digit2.getNum())));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            close();
        }
        return result;
    }

    //математическая операция
    public static int math(int num1, char sign, int num2) {
        try {
            if (sign != '+' && sign != '-' && sign != '*' && sign != '/') {
                throw new Exception("Неверное математическое действие");
            }
            if (sign == '+') {
                return num1 + num2;
            }
            if (sign == '-') {
                return num1 - num2;
            }
            if (sign == '*') {
                return num1 * num2;
            }
            if (sign == '/') {
                return num1 / num2;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            close();
        }
        return Integer.parseInt(null);
    }

    //перевод римских цифр в арабские
    public static int romanToArabic(String inputString) {
        if (inputString.toLowerCase().equals("ii")) {
            return 2;
        } else if (inputString.toLowerCase().equals("iii")) {
            return 3;
        } else if (inputString.toLowerCase().equals("iv")) {
            return 4;
        } else if (inputString.toLowerCase().equals("v")) {
            return 5;
        } else if (inputString.toLowerCase().equals("vi")) {
            return 6;
        } else if (inputString.toLowerCase().equals("vii")) {
            return 7;
        } else if (inputString.toLowerCase().equals("viii")) {
            return 8;
        } else if (inputString.toLowerCase().equals("ix")) {
            return 9;
        } else if (inputString.toLowerCase().equals("x")) {
            return 10;
        }
        return 1;
    }

    //перевод арабских цифр в римские
    public static String arabicToRoman(int inputNum) {
        String result = "";
        try {
            if (inputNum < 1) {
                throw new Exception("Решение меньше единицы!");
            }
            Convertor convertor = new Convertor();
            result = convertor.convert(inputNum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            close();
        }
        return result;
    }

    //проверка, является ли цифра арабской
    public static char testIsItArabic(String inputString) {
        char[] inputChars = inputString.toCharArray();
        if (Character.isDigit(inputChars[0])) {
            return 'a';
        }
        return 'r';
    }

    //проверка числа на корректность
    public static boolean isNumCorrect(String inputNum) {
        try {
            int num = Integer.parseInt(inputNum);
            if (num > 0 && num <= 10) {
                return true;
            } else {
                throw new Exception("Одно из чисел больше 10!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            close();
        }
        return false;
    }

    //проверка, является ли цифра римской
    public static boolean testIsItRoman(String inputString) {
        char[] inputChars = inputString.toCharArray();
        try {
            for (int i = 0; i < inputChars.length; i++) {
                if (Character.toLowerCase(inputChars[i]) == 'i' ||
                        Character.toLowerCase(inputChars[i]) == 'v' ||
                        Character.toLowerCase(inputChars[i]) == 'x'
                ) {
                    return true;
                } else {
                    throw new Exception("Число если и римское то явно больше 10!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            close();
        }
        return false;
    }

    //Аварийное завершение
    public static void close() {
        System.out.println("Программа аварийно завершена");
        System.exit(0);
    }

    //приветственное начало
    public static void begin() {
        System.out.println("Пожалуйста, введите в одну строку, используя пробел, числовое ");
        System.out.println("выражение содержащее одну математическую операцию (+, - , * или /). ");
        System.out.println("Цифры можно использовать арабские или римские (но не одновременно), значением не больше 10 и не меньше 0. ");
        System.out.println("Пример: 1 + 2");
        System.out.println("Или: x / v");
    }

}
