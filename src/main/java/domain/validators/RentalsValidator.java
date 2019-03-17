package domain.validators;

import domain.Rentals;

public class RentalsValidator implements Validator<Rentals>
{
    @Override
    public void validate(Rentals rental) throws ValidatorException {
        String exceptions = "Invalid rental: ";
        if (rental.getId() < 0)
            exceptions += "Id must not be a negative number; ";
        if (!exceptions.equals("Invalid rental: "))
            throw new ValidatorException(exceptions);

    }
}
