import domain.Client;

import ui.UI;
import domain.Movie;
import domain.validators.ClientValidator;
import domain.validators.MovieValidator;
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

        Validator<Movie> movieValidator = new MovieValidator();
        Repository<Integer,Movie> movieRepository = new InMemoryRepository<>(movieValidator);

        Movie movie1 = new Movie("Titanic",1997, "James Cameron");
        movie1.setId(1);
        movieRepository.save(movie1);
        Movie movie2 = new Movie("Pulp Fiction", 1994,"Quentin Tarantino");
        movie2.setId(2);
        movieRepository.save(movie2);

        ClientRentalService crs = new ClientRentalService(clientRepository, movieRepository);
        UI consoleUI = new UI(crs);
        try {
            consoleUI.start();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
