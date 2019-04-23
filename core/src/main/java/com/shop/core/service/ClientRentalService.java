package com.shop.core.service;

import com.shop.core.model.Client;
import com.shop.core.model.Movie;
import com.shop.core.model.validators.ValidatorException;
import com.shop.core.repository.ClientRepository;
import com.shop.core.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * ClientRentalService class for controlling operations on the repositories.
 */
@Service
public class ClientRentalService implements ClientService{

    private static final Logger log = LoggerFactory.getLogger(ClientRentalService.class);

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    MovieRepository movieRepository;


    /**
     * Adds a client to Clients repository.
     * @param client the {@code Client} to be added.
     * @throws ValidatorException if the client is not valid.
     */
    @Override
    public void addClient(Client client) throws ValidatorException {
        log.trace("addClient: client={}", client);
        clientRepository.save(client);//.orElseThrow(() -> new RuntimeException("Client ID already exists."));
        log.trace("addClient --- method finished");
    }

    /**
     * Adds a movie to Movies repository.
     * @param movie the {@code Movie} to be added.
     * @throws ValidatorException if the movie is not valid.
     */
    @Override
    public void addMovie(Movie movie) throws ValidatorException {
        log.trace("addMovie: movie={}", movie);
        movieRepository.save(movie);//.orElseThrow(() -> new RuntimeException("Movie ID already exists."));
        log.trace("addMovie --- method finished");
    }

    /**
     * Updates a client in Clients repository.
     * @param client the {@code Client} to be updated.
     * @throws ValidatorException if the client is not valid.
     */
    @Override
    @Transactional
    public void updateClient(Client client) throws ValidatorException {
        log.trace("updateClient --- method entered");
        log.trace("updateClient: client={}", client);
        clientRepository.findById(client.getId()).ifPresent(
                updateClient -> {
                    updateClient.setFirstName(client.getFirstName());
                    updateClient.setLastName(client.getLastName());
                    updateClient.setAge(client.getAge());
                    updateClient.setId(client.getId());
                }
        );
        log.trace("updateClient --- method finished");
    }

    /**
     * Updates a movie in Movies repository.
     * @param movie the {@code Movie} to be updated.
     * @throws ValidatorException if the movie is not valid.
     */
    @Override
    @Transactional
    public void updateMovie(Movie movie) throws ValidatorException {
        log.trace("updateMovie --- method entered");
        log.trace("updateMovie: movie={}", movie);
        movieRepository.findById(movie.getId()).ifPresent(
                updateMovie -> {
                    updateMovie.setMovieName(movie.getMovieName());
                    updateMovie.setYearOfRelease(movie.getYear());
                    updateMovie.setDirector(movie.getMovieDirector());
                }
        );
        log.trace("updateMovie --- method finished");
    }

    /**
     * Deletes a Client from clients repository.
     * @param id an {@code Integer} representing the id of the Client to be deleted.
     * @return an {@code Optional} - null if there is no Client with the given id; the Movie otherwise.
     */
    @Override
    public void deleteClient(Integer id) {
        log.trace("delete: id={}", id);

        clientRepository.deleteById(id);//.orElseThrow(() -> new ClientNotFoundException("Client ID was not found."));
        log.trace("deleteClient --- method finished");

    }

    /**
     * Deletes a Movie from movies repository.
     * @param id an {@code Integer} representing the id of the movie to be deleted.
     * @return an {@code Optional} - null if there is no Movie with the given id; the Movie otherwise.
     */
    @Override
    public void deleteMovie(Integer id) {
        log.trace("delete: id={}", id);

        movieRepository.deleteById(id);//.orElseThrow(() -> new MovieNotFoundException("Movie ID was not found."));
        log.trace("deleteMovie --- method finished");

    }

    /**
     * Gets all the clients from Clients repository.
     * @return a {@code Set} of all clients.
     */
    @Override
    public Set<Client> getAllClients() {
        log.trace("getAllClients --- method entered");

        Iterable<Client> clients = clientRepository.findAll();

        Set<Client> result = StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());

        log.trace("getAllClients: result={}", result);
        return result;
    }


    /**
     * Gets all the clients from Clients repository sorted by First Name.
     * @return a {@code List} of all movies.
     */
    public List<Client> getAllSortedClients()
    {
        Iterable<Client> clients = clientRepository.findAll();
        return StreamSupport.stream(clients.spliterator(), false)
                .sorted(Comparator.comparing(Client::getLastName))
                .collect(Collectors.toList());
    }
    /**
     * Get the number of movies after a given year.
     * @param year the year
     * @return the number of movies after the given year
     */
    public long getNoMoviesAfterYear(int year) {
        Iterable<Movie> movies = movieRepository.findAll();
        return StreamSupport.stream(movies.spliterator(), false).filter(m -> m.getYear() > year).count();
    }

    /**
     * Gets all the movies from Movies repository.
     * @return a {@code Set} of all movies.
     */
    @Override
    public Set<Movie> getAllMovies()
    {
        log.trace("getAllMovies --- method entered");

        Iterable<Movie> movies = movieRepository.findAll();
        Set<Movie> result =  StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toSet());

        log.trace("getAllClients: result={}", result);
        return result;
    }

    /**
     * Gets all the movies from Movies repository sorted by Name.
     * @return a {@code List} of all movies.
     */
    public List<Movie> getAllSortedMovies()
    {
        Iterable<Movie> movies = movieRepository.findAll();
        return StreamSupport.stream(movies.spliterator(), false)
                .sorted(Comparator.comparing(Movie::getMovieName)).collect(Collectors.toList());
    }

}


