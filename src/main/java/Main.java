import domain.Client;
import domain.Movie;
import domain.ui.UI;
import domain.validators.ClientValidator;
import domain.validators.MovieValidator;
import domain.validators.Validator;
import repository.InMemoryRepository;
import repository.Repository;
import service.ClientRentalService;

import java.lang.management.MonitorInfo;
import java.lang.reflect.Modifier;



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

        Movie movie1 = new Movie("Titanic",1990, "Bla bla");
        movie1.setId(1);
        movieRepository.save(movie1);
        Movie movie2 = new Movie("Pulp fiction", 1993,"Tarantino");
        movie2.setId(2);
        movieRepository.save(movie2);

        ClientRentalService crs = new ClientRentalService(clientRepository, movieRepository);
        UI consoleUI = new UI(crs);
        consoleUI.start();
    }

}
