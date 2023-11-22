package calculator;

import exceptions.IncorrectInput;
import stack.LinkedStack;
import stack.Stack;

public class Calculator {
    private static String phrase;
    private static String[] postfix;
    private static int i;


    public static Double calculator(String input) {
        phrase = input;
        postfix();
        return calculate();

    }

    private static boolean isNotDone(String inputs[], int i) {
        return inputs.length > i + 2;
    }

    private static void postfix() {
        //String decimalNumber = "";
        String[] inputs = phrase.split("");
        String[] fix = new String[inputs.length];
        int index = 0;
        for (i = 0; i < inputs.length; i++) {
            switch (inputs[i]) {
                case "+":
                    if (isNotDone(inputs, i)) {
                        if (inputs[i + 2].equals(".")) {
                            fix[index++] = decimalNumber(inputs);
                        } else {
                            fix[index++] = multiDigit(inputs[++i], inputs);
                        }
                    } else {

                        fix[index++] = multiDigit(inputs[++i], inputs);
                    }
                    fix[index++] = "+";
                    break;
                case "-":
                    if (isNotDone(inputs, i)) {
                        if (inputs[i + 2].equals(".")) {
                            fix[index++] = decimalNumber(inputs);
                        } else
                            fix[index++] = multiDigit(inputs[++i], inputs);
                    } else {
                        fix[index++] = multiDigit(inputs[++i], inputs);
                    }
                    fix[index++] = "-";
                    break;
                case "/":
                    if (isNotDone(inputs, i)) {
                        if (inputs[i + 2].equals(".")) {
                            fix[index++] = decimalNumber(inputs);

                        } else {
                            fix[index++] = multiDigit(inputs[++i], inputs);
                        }
                    } else {
                        fix[index++] = multiDigit(inputs[++i], inputs);
                    }
                    fix[index++] = "/";
                    break;
                case "^":
                    if (isNotDone(inputs, i)) {

                        if (inputs[i + 2].equals(".")) {
                            fix[index++] = decimalNumber(inputs);


                        } else {
                            fix[index++] = multiDigit(inputs[++i], inputs);
                        }
                    } else {
                        fix[index++] = multiDigit(inputs[++i], inputs);
                    }
                    fix[index++] = "^";
                    break;
                case "*":
                    if (isNotDone(inputs, i)) {
                        if (inputs[i + 2].equals(".")) {
                            fix[index++] = decimalNumber(inputs);

                        } else {
                            fix[index++] = multiDigit(inputs[++i], inputs);
                        }
                    } else {
                        fix[index++] = multiDigit(inputs[++i], inputs);
                    }
                    fix[index++] = "*";
                    //i++;
                    break;
                case "!": {
                    fix[index++] = "!";
                    break;
                }
                default: {
                    if (isNumber(inputs[i]))
                        fix[index++] = multiDigit(inputs[i], inputs);
                }
            }
        }
        postfix = fix;
    }

    private static String multiDigit(String first, String[] inputs) {
        String number = first;
        if (inputs.length == i + 1)
            return number;
        if (first.equals("P") && inputs[i + 1].equals("I"))
            return "3.1416";
        else if (first.equals("e"))
            return "2.718";
        while (isNumber(inputs[i + 1])) {
            number += inputs[i++];
            if (inputs.length == i + 1)
                break;
        }
        return number;

    }

    private static String decimalNumber(String[] inputs) {
        String decimalNumber = inputs[i] + ".";
//        if (inputs.length == i + 1)
//            return decimalNumber;
        while (isNumber(inputs[i + 1])) {
            i++;
            decimalNumber += inputs[i];
            if (inputs.length == i + 1)
                break;
        }
        return decimalNumber;
    }

    private static double mul(Double num1, Double num2) {

        return num1 * num2;
    }

    private static double add(Double num1, Double num2) {

        return num1 + num2;

    }

    private static double sub(Double num1, Double num2) {
        return num2 - num1;

    }

    private static double factorial(double num) {
        int factorial = 1;
        if (num == (int) num) {
            for (int j = 2; j < num; j++) {
                factorial *= j;
            }
            return factorial;
        }
        throw new IncorrectInput("The factorial function is defined just for positive integers.");

    }

    private static double pow(Double num1, Double num2) {
        return Math.pow(num2, num1);

    }

    private static double div(Double num1, Double num2) {
        return num2 / num1;

    }

    private static boolean isNumber(String s) {
        return (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5") || s.equals("6") ||
                s.equals("7") || s.equals("8") || s.equals("9") || s.equals("0") || s.equals(".") || s.equals("e") || s.equals("P") || s.equals("PI"));
    }

    private static boolean isNotNumber(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("^") || s.equals("*") || s.equals("!"));
    }

    private static Double calculate() {
        Stack<Double> stack = new LinkedStack<>();
        int j = 0;
        while (postfix[j] != null) {
            if (!isNotNumber(postfix[j])) {
                stack.push(Double.parseDouble(postfix[j++]));
            } else {
                switch (postfix[j++]) {
                    case "+":
                        stack.push(add(stack.pop(), stack.pop()));
                        break;
                    case "-":
                        stack.push(sub(stack.pop(), stack.pop()));
                        break;
                    case "*":
                        stack.push(mul(stack.pop(), stack.pop()));
                        break;
                    case "^":
                        stack.push(pow(stack.pop(), stack.pop()));
                        break;
                    case "!":
                        stack.push(factorial(stack.pop()));
                        break;
                    case "/":
                        stack.push(div(stack.pop(), stack.pop()));
                        break;

                }
            }
        }
        if (stack.size() == 1)
            return stack.pop();
        throw new IncorrectInput();
    }

}
