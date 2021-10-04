package domain.validators;

import domain.baseEntities.Droid;
import exceptions.ValidatorException;

public class DroidValidator implements IValidator<Droid> {
    @Override
    public void validate(Droid entity) throws ValidatorException {
        StringBuilder message = new StringBuilder();

        if (entity.getBatteryTime() < 0) {
            message.append("battery time cannot be negative;");
        }

        if (entity.getPrice() < 0) {
            message.append("price cannot be negative;");
        }
        if (entity.getPowerUsage() < 0) {
            message.append("power usage cannot be negative;");
        }

        if (!message.toString().equals("")) {
            throw new ValidatorException(message.toString());
        }
    }
}