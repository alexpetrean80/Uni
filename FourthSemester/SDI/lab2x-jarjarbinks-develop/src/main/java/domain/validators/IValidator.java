package domain.validators;

import exceptions.ValidatorException;

public interface IValidator<T> {
    void validate(T entity) throws ValidatorException;
}
