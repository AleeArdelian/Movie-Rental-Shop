package movie.rental.common.domain.validators;

/**
 * Validator interface for validating generic types.
 * @param <T> type of the entity to be validated.
 */

//@FunctionalInterface
public interface Validator<T> {

    /**
     * Method for validating data about an entity.
     * @param entity instance of the entity to be validated.
     * @throws ValidatorException if the entity is not valid.
     */
    void validate(T entity) throws ValidatorException;

}
