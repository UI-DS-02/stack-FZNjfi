import calculator.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
        String input = "scanner.nextLine();";
        while (!input.equals("done")) {
            System.out.println("ENTER:");
            input = scanner.nextLine();
                try {
                    System.out.println(Calculator.calculator(input));
                }
                catch (RuntimeException runtimeException)
                {
                    System.out.println(runtimeException.getMessage());
                }
            }

    }
}