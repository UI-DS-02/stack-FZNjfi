package calculator;

public class Calculator {
    private static String phrase;
    public static String calculator(String input)
    {
        phrase=input;
        postfix();
        return calculate();

    }
    private static void postfix()
    {

    }
    private static String calculate()
    {
        return "";
    }

}
