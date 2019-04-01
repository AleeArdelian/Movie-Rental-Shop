package movie.rental.server.domain.validators;

import movie.rental.server.domain.Rental;

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
