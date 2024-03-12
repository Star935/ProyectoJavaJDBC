package exceptions;

public class InsufficientAmountException extends Exception {
    public InsufficientAmountException() {
        super("There is not enough quantity available for decreasing!");
    }
}
