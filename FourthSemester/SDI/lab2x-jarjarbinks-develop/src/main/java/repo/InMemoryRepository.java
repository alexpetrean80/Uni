package repo;

import domain.baseEntities.BaseEntity;
import domain.validators.IValidator;
import exceptions.ValidatorException;

import java.util.*;

/**
 *
 * @param <ID>
 * @param <T>
 */

public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements repo.IRepository<ID, T> {
    /**
     *
     */

    private Map<ID, T> entities;
    private IValidator<T> validator;

    public InMemoryRepository(IValidator<T> validator){
        this.validator = validator;
        this.entities = new HashMap<>();
    }

    /*
     * Find the entity with the given {@code id}.
     *
     * @param id
     *            must be not null.
     * @return an {@code Optional} encapsulating the entity with the given id.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }

    /**
     *
     * @return all objects
     */
    @Override
    public Iterable<T> findAll() {
        return new HashSet<>(entities.values());
    }

    /**
     *
     * @param entity
     *            must not be null.
     * @ return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws ValidatorException
     *          if the entity is not valid
     * @throws IllegalArgumentException
     *          if the entity is null
     */
    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }


    /**
     *
     * @param id
     *            must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id
     * @throws IllegalArgumentException
     *          if the given element is null
     */
    @Override
    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.remove(id));
    }


    /**
     * Updates the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was updated otherwise (e.g. id does not exist) returns the
     *         entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
