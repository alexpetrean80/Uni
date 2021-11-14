package exception;

public class UnknownKeyException extends CustomException {

    public UnknownKeyException(){
        super("Unknown key.");
    }
}
