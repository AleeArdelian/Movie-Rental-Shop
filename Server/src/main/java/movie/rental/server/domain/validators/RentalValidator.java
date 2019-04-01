package movie.rental.server.domain.validators;

import movie.rental.common.domain.Rental;
import movie.rental.common.domain.validators.Validator;
import movie.rental.common.domain.validators.ValidatorException;

public class RentalValidator implements Validator<Rental>
{
    @Override
    public void validate(Rental rental) throws ValidatorException {
        String exceptions = "Invalid rental: ";
        if (rental.getId() < 0)
            exceptions += "Id must not be a negative number; ";
        if (!exceptions.equals("Invalid rental: "))
            throw new ValidatorException(exceptions);

    }
}
