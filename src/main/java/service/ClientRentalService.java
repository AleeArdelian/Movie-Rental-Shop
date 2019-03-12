package service;

import domain.Client;
import domain.Movie;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * ClientRentalService class for controlling operations on the repositories.
 */
public class ClientRentalService {

    private Repository<Integer, Client> clientRepository;
    private Repository<Integer, Movie> movieRepository;

    /**
     * Constructor for ClientRentalService.
     * @param crs a {@code Repository} instance for Clients repository.
     * @param mov a {@code Repository} instance for Movies repository.
     */
    public ClientRentalService(Repository<Integer,Client> crs, Repository<Integer,Movie> mov)
    {
        clientRepository = crs;
        movieRepository = mov;
    }

    /**
     * Adds a client to Clients repository.
     * @param client the {@code Client} to be added.
     * @throws ValidatorException if the client is not valid.
     */
    public void addClient(Client client) throws ValidatorException {
        clientRepository.save(client);
    }

    /**
     * Adds a movie to Movies repository.
     * @param movie the {@code Movie} to be added.
     * @throws ValidatorException if the movie is not valid.
     */
    public void addMovie(Movie movie) throws ValidatorException
    {
        movieRepository.save(movie);
    }

    /**
     * Updates a client in Clients repository.
     * @param client the {@code Client} to be updated.
     * @throws ValidatorException if the client is not valid.
     */
    public void updateClient(Client client) throws ValidatorException
    {
        clientRepository.update(client);
    }

    /**
     * Updates a movie in Movies repository.
     * @param movie the {@code Movie} to be updated.
     * @throws ValidatorException if the movie is not valid.
     */
    public void updateMovie(Movie movie) throws ValidatorException
    {
        movieRepository.update(movie);
    }

    /**
     * Deletes a Client from clients repository.
     * @param id an {@code Integer} representing the id of the Client to be deleted.
     * @return an {@code Optional} - null if there is no Client with the given id; the Movie otherwise.
     */
    public Optional<Client> deleteClient(Integer id) {
        return clientRepository.delete(id);
    }

    /**
     * Deletes a Movie from movies repository.
     * @param id an {@code Integer} representing the id of the movie to be deleted.
     * @return an {@code Optional} - null if there is no Movie with the given id; the Movie otherwise.
     */
    public Optional<Movie> deleteMovie(Integer id) {
        return movieRepository.delete(id);
    }

    /**
     * Gets all the clients from Clients repository.
     * @return a {@code Set} of all clients.
     */
    public Set<Client> getAllClients()
    {
        Iterable<Client> clients = clientRepository.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Gets all the movies from Movies repository.
     * @return a {@code Set} of all movies.
     */
    public Set<Movie> getAllMovies()
    {
        Iterable<Movie> movies = movieRepository.findAll();
        return StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toSet());
    }
}
