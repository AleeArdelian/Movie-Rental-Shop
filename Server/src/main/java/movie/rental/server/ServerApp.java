package movie.rental.server;

import movie.rental.common.service.RentalService;
import movie.rental.common.domain.Message;
import movie.rental.common.domain.Client;
import movie.rental.common.domain.Movie;
import movie.rental.common.domain.Rental;
import movie.rental.common.domain.validators.Validator;
import movie.rental.server.domain.validators.ClientValidator;
import movie.rental.server.domain.validators.MovieValidator;
import movie.rental.server.domain.validators.RentalValidator;
import movie.rental.server.repository.BD.ClientDBRepository;
import movie.rental.server.repository.BD.MovieDBRepository;
import movie.rental.server.repository.BD.RentalDBRepository;

import movie.rental.server.service.Service;
import movie.rental.server.service.ServerRentalService;
import movie.rental.server.tcp.TcpServer;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * author: radu
 */
public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());

        TcpServer tcpServer = new TcpServer(executorService, RentalService.SERVER_PORT);

        Validator<Client> clientValidator = new ClientValidator();
        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Rental> rentalValidator = new RentalValidator();

        ClientDBRepository clientRepository = new ClientDBRepository(clientValidator);
        MovieDBRepository movieRepository = new MovieDBRepository(movieValidator);
        RentalDBRepository rentalRepository = new RentalDBRepository(rentalValidator);

        //Repository<Integer, Client> clientRepository = new ClientFileRepository(clientValidator, "data/clients");

        Service crs = new Service(clientRepository, movieRepository, rentalRepository);

        RentalService rentalService = new ServerRentalService(executorService, crs);

        tcpServer.addHandler(RentalService.SET_PAGE_SIZE, (request) -> {
            Integer size = (Integer)request.getBody();
            rentalService.setPageSize(size);
            return new Message(Message.OK);
        });

        tcpServer.addHandler(RentalService.ADD_CLIENT, (request) -> {
            Client client = (Client)request.getBody();
            try {
                rentalService.addClient(client);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.UPDATE_CLIENT, (request) -> {
            Client client = (Client)request.getBody();
            try {
                rentalService.updateClient(client);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.DELETE_CLIENT, (request) -> {
            Integer id = (Integer)request.getBody();
            try {
                rentalService.deleteClient(id);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_CLIENTS, (request) -> {
            try {
                Future<Set<Client>> clients = rentalService.getNextClients();
                return new Message(Message.OK, clients.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_ALL_CLIENTS, (request) -> {
            try {
                Future<Set<Client>> clients = rentalService.getAllClients();
                return new Message(Message.OK, clients.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_SORTED_CLIENTS, (request) -> {
            try {
                Future<List<Client>> clients = rentalService.getAllSortedClients();
                return new Message(Message.OK, clients.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.ADD_MOVIE, (request) -> {
            Movie movie = (Movie)request.getBody();
            try {
                rentalService.addMovie(movie);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.UPDATE_MOVIE, (request) -> {
            Movie movie = (Movie)request.getBody();
            try {
                rentalService.updateMovie(movie);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.DELETE_MOVIE, (request) -> {
            Integer id = (Integer)request.getBody();
            try {
                rentalService.deleteMovie(id);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_MOVIES, (request) -> {
            try {
                Future<Set<Movie>> movies = rentalService.getNextMovies();
                return new Message(Message.OK, movies.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_ALL_MOVIES, (request) -> {
            try {
                Future<Set<Movie>> movies = rentalService.getAllMovies();
                return new Message(Message.OK, movies.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_SORTED_MOVIES, (request) -> {
            try {
                Future<List<Movie>> movies = rentalService.getAllSortedMovies();
                return new Message(Message.OK, movies.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.ADD_RENTAL, (request) -> {
            Rental rental = (Rental)request.getBody();
            try {
                rentalService.addRental(rental);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.DELETE_RENTAL, (request) -> {
            Integer id = (Integer)request.getBody();
            try {
                rentalService.deleteRental(id);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_RENTALS, (request) -> {
            try {
                Future<Set<Rental>> rentals = rentalService.getNextRentals();
                return new Message(Message.OK, rentals.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_ALL_RENTALS, (request) -> {
            try {
                Future<Set<Rental>> rentals = rentalService.getAllRentals();
                return new Message(Message.OK, rentals.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.start();
    }

}
