package exceptions;

public class InexistentPurchaseException extends exceptions.DealershipException{
    /**
     * @param message
     */
    public InexistentPurchaseException(String message) {
        super(message);
    }
}
