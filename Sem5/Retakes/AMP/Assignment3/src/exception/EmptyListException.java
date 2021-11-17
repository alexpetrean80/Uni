package exception;

public class EmptyListException extends CustomException {
    public EmptyListException(){
        super("The list is empty");
    }
}
