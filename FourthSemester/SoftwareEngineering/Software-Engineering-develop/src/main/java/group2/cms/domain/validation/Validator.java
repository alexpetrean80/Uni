package group2.cms.domain.validation;

import group2.cms.domain.BaseEntity;
import group2.cms.exceptions.ValidationException;

public interface Validator <T extends BaseEntity<?>> {
    void validate(T entity) throws ValidationException;
}
