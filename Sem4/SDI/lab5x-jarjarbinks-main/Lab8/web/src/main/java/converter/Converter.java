package converter;

import domain.baseEntities.BaseEntity;
import dto.Dto;

import java.io.Serializable;

public interface Converter<ID extends Serializable, M, D extends Dto<ID>> {
    D toDto(M model);
}
