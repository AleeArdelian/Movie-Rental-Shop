import domain.Client;
import domain.ui.UI;
import domain.validators.ClientValidator;
import domain.validators.Validator;
import repository.InMemoryRepository;
import repository.Repository;
import service.ClientRentalService;

public class Main {

    public static void main(String[] args) {
        Validator<Client> clientValidator = new ClientValidator();
        Repository<Integer, Client> clientRepository = new InMemoryRepository<>(clientValidator);
        Client client1= new Client("Alexandru","Balea", 20);
        client1.setId(1);
        clientRepository.save(client1);
        Client client2= new Client("Alexandra","Ardelian", 20);
        client2.setId(2);
        clientRepository.save(client2);

        ClientRentalService crs = new ClientRentalService(clientRepository);
        UI consoleUI = new UI(crs);
        consoleUI.start();
    }

}
