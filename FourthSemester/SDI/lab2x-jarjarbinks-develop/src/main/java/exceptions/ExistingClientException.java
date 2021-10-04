package exceptions;

public class ExistingClientException extends exceptions.DealershipException{
    /**
     * @param message
     */
    public ExistingClientException(String message) {
        super(message);
    }
}
