package exception;

public class NotBooleanException extends MyException{

    public NotBooleanException(){
        super("Conditional expression is not boolean.");
    }
}
