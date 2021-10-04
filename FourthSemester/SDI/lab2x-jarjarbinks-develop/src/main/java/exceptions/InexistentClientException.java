package exceptions;

public class InexistentClientException extends DealershipException{
    /**
     * @param message
     */
    public InexistentClientException(String message) {
        super(message);
    }
}
