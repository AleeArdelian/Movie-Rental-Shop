import domain.Client;
import domain.Rental;
import domain.validators.*;
import repository.BD.ClientDBRepository;
import repository.BD.MovieDBRepository;
import repository.BD.RentalDBRepository;
import repository.file.ClientFileRepository;
import repository.file.MovieFileRepository;
import repository.file.RentalFileRepository;
import repository.mem.InMemoryRepository;
import repository.paging.PagingRepository;
import repository.xml.XMLClientsRepository;
import repository.xml.XMLMoviesRepository;
import repository.xml.XMLRentalsRepository;
import ui.MainMenu;
import domain.Movie;
import repository.Repository;
import service.ClientRentalService;
import ui.AbstractMenu;

public class Main {

    public static void main(String[] args) {

        Validator<Client> clientValidator = new ClientValidator();
        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Rental> rentalValidator = new RentalValidator();

        PagingRepository<Integer, Client> clientRepository = null;
        PagingRepository<Integer,Movie> movieRepository = null;
        PagingRepository<Integer, Rental> rentalRepository = null;

        String repoType = "mem";

        switch (repoType) {
            case "mem":
                clientRepository = new InMemoryRepository<>(clientValidator);
                populateClientRepo(clientRepository);
                movieRepository = new InMemoryRepository<>(movieValidator);
                populateMovieRepo(movieRepository);
                rentalRepository = new InMemoryRepository<>(rentalValidator);
                break;
            case "file":
                clientRepository = new ClientFileRepository(clientValidator, "./data/clients");
                movieRepository = new MovieFileRepository(movieValidator, "./data/movies");
                rentalRepository = new RentalFileRepository(rentalValidator, "./data/rentals");
                break;
            case "db":
                clientRepository = new ClientDBRepository(clientValidator);
                movieRepository = new MovieDBRepository(movieValidator);
                rentalRepository = new RentalDBRepository(rentalValidator);
                /*
            case "xml":
                clientRepository = new XMLClientsRepository("data/clients.xml", clientValidator);
                movieRepository = new XMLMoviesRepository("data/movies.xml", movieValidator);
                rentalRepository = new XMLRentalsRepository("data/rentals.xml", rentalValidator);*/
        }


        ClientRentalService crs = new ClientRentalService(clientRepository, movieRepository, rentalRepository);

        if (repoType.equals("mem")) addRentals(crs);


        AbstractMenu ui = new MainMenu(crs);
        ui.run();
    }

    private static void populateClientRepo(Repository<Integer, Client> clientRepository) {
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
    }

    private static void populateMovieRepo(Repository<Integer, Movie> movieRepository) {
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
    }

    private static void addRentals(ClientRentalService crs) {
        /*
         * Add some rentals in the repo
         */
        Rental rental1 = new Rental(1, 1);
        rental1.setId(1);
        crs.addRental(rental1);
        Rental rental2 = new Rental(2, 2);
        rental2.setId(2);
        crs.addRental(rental2);
        Rental rental3 = new Rental(3, 3);
        rental3.setId(3);
        crs.addRental(rental3);
    }
}