import domain.Client;
import ui.UI;
import domain.validators.ClientValidator;
import domain.validators.Validator;
import repository.InMemoryRepository;
import repository.Repository;
import service.ClientRentalService;

public class Main {

    public static void main(String[] args) {
        Validator<Client> clientValidator = new ClientValidator();
        Repository<Integer, Client> clientRepository = new InMemoryRepository<>(clientValidator);
        ClientRentalService crs = new ClientRentalService(clientRepository);
        UI consoleUI = new UI(crs);
        consoleUI.start();
    }

}
