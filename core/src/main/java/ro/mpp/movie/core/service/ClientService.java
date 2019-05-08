package ro.mpp.movie.core.service;

import ro.mpp.movie.core.model.Client;
import ro.mpp.movie.core.model.Movie;
import ro.mpp.movie.core.model.Rental;

import java.util.List;
import java.util.Set;

public interface ClientService {

    Client addClient(Client client);
    Movie addMovie(Movie movie);
    Client updateClient(Integer id, Client client);
    Movie updateMovie(Integer id, Movie movie);
    void deleteClient(Integer id);
    void deleteMovie(Integer id);
    List<Client> getAllClients();
    List<Movie> getAllMovies();
    Rental addRental(Rental rental);
//    Rental updateRental(Integer id, Rental rental);
    Rental deleteRental(Integer id);
    List<Rental> getAllRentals();

}
