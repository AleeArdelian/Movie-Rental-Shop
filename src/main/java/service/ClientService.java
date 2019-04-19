package service;

import domain.Client;
import domain.Movie;

import java.util.Set;

public interface ClientService {
    void addClient(Client client);
    void addMovie(Movie movie);
    void updateClient(Client client);
    void updateMovie(Movie movie);
    void deleteClient(Integer id);
    void deleteMovie(Integer id);
    Set<Client> getAllClients();
    Set<Movie> getAllMovies();

}
