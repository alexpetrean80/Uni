package domain.validators;

import domain.baseEntities.Client;
import exceptions.ValidatorException;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ValidatorException {
        var msgBuilder = new StringBuilder();

        if (entity.getName().isEmpty()) {
            msgBuilder.append("name cannot be empty;");
        }

        if (entity.getAddress().isEmpty()) {
            msgBuilder.append("address cannot be empty;");
        }

        if (entity.getPhoneNumber().isEmpty()) {
            msgBuilder.append("phone number cannot be empty;");
        }

        if (msgBuilder.isEmpty()) {
            return;
        }
        throw new ValidatorException(msgBuilder.toString());
    }
}
