package exceptions;

public class ToyNotLargerThanException extends Exception {
    public ToyNotLargerThanException() {
        super("There isn't any toy beyond that amount!");
    }
}

