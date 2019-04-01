package movie.rental.server.repository.mem;

import movie.rental.common.domain.BaseEntity;
import movie.rental.common.domain.validators.Validator;
import movie.rental.common.domain.validators.ValidatorException;
import movie.rental.server.repository.paging.Page;
import movie.rental.server.repository.paging.Pageable;
import movie.rental.server.repository.paging.PagingRepository;
import movie.rental.server.repository.paging.impl.Paginator;

import java.io.Serializable;
import java.util.*;

/**
 * InMemoryRepository class for CRUD operations on in memory movie.rental.server.repository for generic types.
 * Implements movie.rental.server.repository interface.
 * @param <ID> type of the id of the entity
 * @param <T> type of the entity; must extend BaseEntity
 */
public class InMemoryRepository<ID extends Serializable, T extends BaseEntity<ID>> implements PagingRepository<ID, T> {

    private Map<ID, T> entities;
    private Validator<T> validator;

    /**
     * Constructor for the InMemoryRepository.
     * @param validator a {@code Validator} for validating entities in the movie.rental.server.repository.
     */
    public InMemoryRepository(Validator<T> validator) {
        this.entities = new HashMap<>();
        this.validator = validator;
    }

    /**
     * Object of type T if the id exists in the movie.rental.server.repository, null otherwise
     * @param id a {@code Integer} representing the object`s id
     * @return a {@code Object of type T} / null
     */
    @Override
    public Optional<T> findOne(ID id) {
        Optional<ID> idOpt = Optional.ofNullable(id);
        idOpt.orElseThrow(() -> new IllegalArgumentException("Id must not be null!"));
        return Optional.ofNullable(entities.get(id));
    }

    /**
     * Gets all the entities found in the movie.rental.server.repository
     * @return an {@code Iterable} instance representing a set of all the entities in the movie.rental.server.repository
     */

    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = new HashSet<>(entities.values());
        return allEntities;
    }

    /**
     * Adds the entity object to the movie.rental.server.repository
     * @param entity an object of type T
     * @throws ValidatorException if @param entity is not valid
     * @return a {@code Map} with the entity object added
     */
    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        Optional<T> entityOpt = Optional.ofNullable(entity);
        entityOpt.orElseThrow(() -> new IllegalArgumentException("Entity must not be null!"));
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    /**
     * Deletes the object of type T from the movie.rental.server.repository if the id exists
     * @param id a {@code Integer}
     * @throws IllegalArgumentException if the id is null
     * @return a {@code Map} without the object with the given id
     */
    @Override
    public Optional<T> delete(ID id) {
        Optional<ID> idOpt = Optional.ofNullable(id);
        idOpt.orElseThrow(() -> new IllegalArgumentException("Id must not be null!"));
        return Optional.ofNullable(entities.remove(id));
    }

    /**
     * Update the entity object found in the movie.rental.server.repository
     * @param entity an object of type T
     * @throws IllegalArgumentException if @@id is null
     * @throws ValidatorException if @entity is not valid
     * @return a {@code Map} with the entity object modified
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        Optional<T> entityOpt = Optional.ofNullable(entity);
        entityOpt.orElseThrow(() -> new IllegalArgumentException("Entity must not be null!"));
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return Paginator.paginate(this.findAll(), pageable);
    }
}
