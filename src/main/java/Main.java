import domain.Client;

import domain.Rentals;
import domain.validators.*;
import ui.MainMenu;
import domain.Movie;
import repository.InMemoryRepository;
import repository.Repository;
import service.ClientRentalService;
import ui.AbstractMenu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Validator<Client> clientValidator = new ClientValidator();
        Repository<Integer, Client> clientRepository = new InMemoryRepository<>(clientValidator);

        /*
         * Add some clients in clients repository.
         */
        Client client1= new Client("Alexandru","Balea", 20);
        client1.setId(1);
        clientRepository.save(client1);
        Client client2= new Client("Alexandra","Ardelian", 20);
        client2.setId(2);
        clientRepository.save(client2);
        Client client3= new Client("Catalin","Belcianu", 20);
        client3.setId(3);
        clientRepository.save(client3);
        Client client4= new Client("Cristina","Braga", 20);
        client4.setId(4);
        clientRepository.save(client4);
        Client client5= new Client("Diana","Achim", 20);
        client5.setId(5);
        clientRepository.save(client5);

        Validator<Movie> movieValidator = new MovieValidator();
        Repository<Integer,Movie> movieRepository = new InMemoryRepository<>(movieValidator);

        /*
         * Add some movies in the movie repository.
         */
        Movie movie1 = new Movie("Titanic",1997, "James Cameron");
        movie1.setId(1);
        movieRepository.save(movie1);
        Movie movie2 = new Movie("Pulp Fiction", 1994,"Quentin Tarantino");
        movie2.setId(2);
        movieRepository.save(movie2);
        Movie movie3 = new Movie("Django", 2012,"Quentin Tarantino");
        movie3.setId(3);
        movieRepository.save(movie3);
        Movie movie4 = new Movie("A Bronx Tale", 1993,"Robert De Niro");
        movie4.setId(4);
        movieRepository.save(movie4);
        Movie movie5 = new Movie("Casino", 1995,"Martin Scorsese");
        movie5.setId(5);
        movieRepository.save(movie5);

        /*
         * Add some rentals in the movie repository.
         */

        Validator<Rentals> rentalValidator = new RentalsValidator();
        Repository<Integer,Rentals> rentalRepository = new InMemoryRepository<>(rentalValidator);

        Rentals rental1 = new Rentals(1, 1);
        rental1.setId(1);
        rentalRepository.save(rental1);
        Rentals rental2 = new Rentals(2, 2);
        rental2.setId(2);
        rentalRepository.save(rental2);
        Rentals rental3 = new Rentals(3, 3);
        rental3.setId(3);
        rentalRepository.save(rental3);


        ClientRentalService crs = new ClientRentalService(clientRepository, movieRepository, rentalRepository);
        AbstractMenu ui = new MainMenu(crs);
        ui.run();
    }

}