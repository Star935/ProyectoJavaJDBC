package exceptions;

public class ToyNotFoundException extends Exception{
    public ToyNotFoundException() {
        super("The toy does not exist in the list!");
    }
}
