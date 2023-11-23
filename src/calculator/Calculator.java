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

    private static void postfix() {
        Stack<String> stack = new LinkedStack<>();
        String[] inputs = phrase.split("");
        String[] fix = new String[inputs.length];
        int index = 0;
        for (i = 0; i < inputs.length; i++) {
            if (isNumber(inputs[i]))
                fix[index++] = multiDigit(inputs[i], inputs);
            else if (inputs[i].equals("(")) {
                stack.push(inputs[i]);
            } else if (inputs[i].equals(")")) {
                while (!stack.top().equals("(")) {
                    fix[index++] = stack.pop();
                    if (stack.isEmpty())
                        throw new IncorrectInput();
                }
                stack.pop();
            } else if (!inputs[i].equals(" ")) {
                while (!stack.isEmpty()
                        && priority(inputs[i]) <= priority(stack.top())) {
                    fix[index++] = stack.pop();
                }
                stack.push(inputs[i]);
            }
        }
        while (!stack.isEmpty())
            fix[index++] = stack.pop();
        postfix = fix;
    }

    private static int priority(String s) {
        switch (s) {
            case "+":
            case "-":
                return 1;
            case "/":
            case "*":
                return 2;
            case "^":
                return 3;
            case "!":
                return 4;

        }
        return 0;
    }

    private static String multiDigit(String first, String[] inputs) {
        String number = first;
        if (first.equals("e"))
            return "2.718";
        else if (inputs.length == i + 1){
            return number;}
        else if (first.equals("P") && inputs[i + 1].equals("I")){
            i++;
            return "3.1416";}
        while (isNumber(inputs[i + 1])) {
            number += inputs[++i];
            if (inputs.length == i + 1)
                break;
        }
        return number;

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

    private static double negative(Double num1) {
        return -num1;

    }

    private static double factorial(double num) {
        int factorial = 1;
        if (num == (int) num) {
            for (int j = 2; j <= num; j++) {
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
        while (j < postfix.length && postfix[j] != null) {
            if (!isNotNumber(postfix[j])) {
                stack.push(Double.parseDouble(postfix[j++]));
            } else {
                switch (postfix[j++]) {
                    case "+":
                        stack.push(add(stack.pop(), stack.pop()));
                        break;
                    case "-":
                        if (isNotNumber(postfix[j-3])||stack.size()==1)
                            stack.push(negative(stack.pop()));
                        else
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
