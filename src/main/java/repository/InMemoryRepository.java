package repository;

import domain.BaseEntity;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * InMemoryRepository class for CRUD operations on in memory repository for generic types.
 * Implements Repository interface.
 * @param <ID> type of the id of the entity
 * @param <T> type of the entity; must extend BaseEntity
 */
public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T>{

    private Map<ID, T> entities;
    private Validator<T> validator;

    /**
     * Constructor for the InMemoryRepository.
     * @param validator a {@code Validator} for validating entities in the repository.
     */
    public InMemoryRepository(Validator<T> validator) {
        this.entities = new HashMap<>();
        this.validator = validator;
    }

    /**
     * Object of type T if the id exists in the repository, null otherwise
     * @param id a {@code Integer} representing the object`s id
     * @return a {@code Object of type T} / null
     */
    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null!");
        }
        return Optional.ofNullable(entities.get(id));
    }

    /**
     * Create a set of all the entities found in the repository
     * @return a {@code Set} of all the entities of type T
     */

    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    /**
     *  Adds the entity object to the repository
     * @param entity an object of type T
     * @throws ValidatorException if @param entity is not valid
     * @return a {@code Map} with the entity object added
     */
    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null!");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    /**
     * Deletes the object of type T from the repository if the id exists
     * @param id a {@code Integer}
     * @throws IllegalArgumentException if the id is null
     * @return a {@code Map} without the object with the given id
     */
    @Override
    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null!");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    /**
     * Update the entity object found in the repository
     * @param entity an object of type T
     * @throws IllegalArgumentException if @@id is null
     * @throws ValidatorException if @entity is not valid
     * @return a {@code Map} with the entity object modified
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null!");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }

}
