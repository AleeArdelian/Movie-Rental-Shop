package domain.validators;

import domain.Client;

public class ClientValidator implements Validator<Client> {

    @Override
    public void validate(Client client) throws ValidatorException {
        String exceptions = "Invalid client: ";
        if (client.getId() < 0)
            exceptions += "Id must not be a negative number; ";
        if (client.getAge() < 0 || client.getAge() > 100)
            exceptions += "Invalid age; ";
        else if (client.getAge() >= 0 && client.getAge() <= 12)
            exceptions += "Client is underage; ";
        if (!exceptions.equals("Invalid client: "))
            throw new ValidatorException(exceptions);
    }

}
