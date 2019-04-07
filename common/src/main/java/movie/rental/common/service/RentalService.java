package movie.rental.common.service;

import movie.rental.common.domain.Client;
import movie.rental.common.domain.Movie;
import movie.rental.common.domain.Rental;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * author: radu
 */
public interface RentalService {

    String SERVER_HOST = "localhost";
    int SERVER_PORT = 1234;

    String SET_PAGE_SIZE = "setPageSize";
    void setPageSize(Integer size);

    String ADD_CLIENT = "addClient";
    CompletableFuture<Boolean> addClient(Client client);

    String UPDATE_CLIENT = "updateClient";
    void updateClient(Client client);

    String DELETE_CLIENT = "deleteClient";
    void deleteClient(Integer id);

    String GET_CLIENTS = "getNextClients";
    CompletableFuture<Set<Client>> getNextClients();

    String GET_ALL_CLIENTS = "getAllClients";
    CompletableFuture<Set<Client>> getAllClients();

    String GET_SORTED_CLIENTS = "getAllSortedClients";
    Future<List<Client>> getAllSortedClients();

    String ADD_MOVIE = "addMovie";
    void addMovie(Movie movie);

    String UPDATE_MOVIE = "updateMovie";
    void updateMovie(Movie movie);

    String DELETE_MOVIE = "deleteMovie";
    void deleteMovie(Integer id);

    String GET_MOVIES = "getNextMovies";
    Future<Set<Movie>> getNextMovies();

    String GET_ALL_MOVIES = "getAllMovies";
    Future<Set<Movie>> getAllMovies();

    String GET_SORTED_MOVIES = "getAllSortedMovies";
    Future<List<Movie>> getAllSortedMovies();

    String ADD_RENTAL = "addRental";
    void addRental(Rental rental);

    String DELETE_RENTAL = "deleteRental";
    void deleteRental(Integer id);

    String GET_RENTALS = "getNextRentals";
    Future<Set<Rental>> getNextRentals();

    String GET_ALL_RENTALS = "getAllRentals";
    Future<Set<Rental>> getAllRentals();

}
