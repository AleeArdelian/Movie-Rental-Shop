package domain.validators;

import java.util.Set;

public class UserChoiceValidator implements Validator<String>{

    private Set<String> options;

    public void setOptions(Set<String> options) {
        this.options = options;
    }

    @Override
    public void validate(String choice) throws ValidatorException {
        String exceptions = "Invalid choice: ";
        if (!options.contains(choice))
            exceptions += "Choose a valid option; ";
        if (!exceptions.equals("Invalid choice: "))
            throw new ValidatorException(exceptions);
    }
}
