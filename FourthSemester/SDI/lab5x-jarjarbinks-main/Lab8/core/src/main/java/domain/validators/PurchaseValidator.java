package domain.validators;

import domain.baseEntities.Purchase;
import exceptions.ValidatorException;

public class PurchaseValidator implements Validator<Purchase> {
    @Override
    public void validate(Purchase entity) throws ValidatorException {
        var msgBuilder = new StringBuilder();



        if (entity.getClientID() == 0) {
            msgBuilder.append("client id cannot be 0;");
        }

        if (entity.getDroidID() == 0) {
            msgBuilder.append("droid id cannot be 0;");
        }

        if (entity.getTotalPrice() <= 0) {
            msgBuilder.append("total price cannot be smaller than 0 or 0;");
        }
        if (msgBuilder.isEmpty()) {
            return;
        }
        throw new ValidatorException(msgBuilder.toString());
    }
}
