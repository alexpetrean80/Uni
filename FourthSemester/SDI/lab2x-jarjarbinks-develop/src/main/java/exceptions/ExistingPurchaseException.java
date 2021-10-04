package exceptions;

public class ExistingPurchaseException extends exceptions.DealershipException{
    /**
     * @param message
     */
    public ExistingPurchaseException(String message) {
        super(message);
    }
}
