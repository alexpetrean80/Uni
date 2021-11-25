package exception;

public class EmptyListException extends MyException {

    public EmptyListException() {
        super("The list is empty");
    }
}
