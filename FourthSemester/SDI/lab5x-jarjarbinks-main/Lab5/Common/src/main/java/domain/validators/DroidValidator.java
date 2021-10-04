package domain.validators;

import domain.baseEntities.Droid;
import exceptions.ValidatorException;

public class DroidValidator implements Validator<Droid> {
    @Override
    public void validate(Droid entity) throws ValidatorException {
        var msgBuilder = new StringBuilder();

        if (entity.getBatteryTime() < 0) {
            msgBuilder.append("battery time cannot be negative;");
        }

        if (entity.getPrice() < 0) {
            msgBuilder.append("price cannot be negative;");
        }
        if (entity.getPowerUsage() < 0) {
            msgBuilder.append("power usage cannot be negative;");
        }

        if (msgBuilder.isEmpty()) {
            return;
        }
        throw new ValidatorException(msgBuilder.toString());
    }
}
