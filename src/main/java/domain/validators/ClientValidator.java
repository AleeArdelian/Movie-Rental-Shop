package domain.validators;

import domain.Client;

public class ClientValidator implements Validator<Client> {

    @Override
    public void validate(Client client) throws ValidatorException {
        String exceptions = "";
        if (client.getAge() < 0 && client.getAge() > 100)
            exceptions += "Invalid age\n";
        else if (client.getAge() >= 0 && client.getAge() <= 12)
            exceptions += "Client is underage";
        if (!exceptions.equals(""))
            throw new ValidatorException(exceptions);
    }

}
