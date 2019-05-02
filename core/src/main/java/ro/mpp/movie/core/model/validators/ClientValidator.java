package ro.mpp.movie.core.model.validators;

import ro.mpp.movie.core.model.Client;


/**
 * ClientValidator class for validating data about a client.
 * Implements Validator interface.
 */
public class ClientValidator implements Validator<Client> {

    /**
     * Method for validating information about a client.
     * A Client is valid if: - its id is not negative
     *                       - the client age is not negative and the client is not underage (12)
     * @param client a {@code Client} instance to be validated.
     * @throws ValidatorException if data about the client is not valid.
     */

    @Override
    public void validate(Client client) throws ValidatorException {
        String exceptions = "Invalid client: ";
        if (client.getId() < 0)
            exceptions += "Id must not be a negative number; ";
        if (client.getAge() < 0 || client.getAge() > 100)
            exceptions += "Invalid age; ";
        else if (client.getAge() >= 0 && client.getAge() < 12)
            exceptions += "Client is underage; ";
        if (!exceptions.equals("Invalid client: "))
            throw new ValidatorException(exceptions);
    }

}
