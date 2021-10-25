package group2.cms.service.DTO;

import group2.cms.domain.BaseEntity;

import java.util.Collection;

public interface DTOConverter<E extends BaseEntity<?>, T extends EntityDTO<E>> {
    E dtoToEntity(T dto);
    T entityToDto(E entity);
    EntitiesDTO<E, T> entitiesToDTO(Collection<E> entities);
}
