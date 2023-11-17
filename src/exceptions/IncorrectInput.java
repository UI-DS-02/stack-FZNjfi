package exceptions;

public class IncorrectInput extends RuntimeException {
    public IncorrectInput() {
    super("The entered phrase is incorrect.\nTry Again\n");
    }

    public IncorrectInput(String massage) {
    super(massage);
    }
}
