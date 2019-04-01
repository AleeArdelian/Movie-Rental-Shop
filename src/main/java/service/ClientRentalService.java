package service;

import domain.Client;
import domain.Movie;
import domain.Rental;
import domain.exceptions.ClientNotFoundException;
import domain.exceptions.MovieAlreadyRentedException;
import domain.exceptions.MovieNotFoundException;
import domain.exceptions.RentalNotFoundException;
import domain.validators.ValidatorException;
import repository.Repository;
import repository.paging.Page;
import repository.paging.Pageable;
import repository.paging.PagingRepository;
import repository.paging.impl.PageableImpl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * ClientRentalService class for controlling operations on the repositories.
 */
public class ClientRentalService {

    private PagingRepository<Integer, Client> clientRepository;
    private PagingRepository<Integer, Movie> movieRepository;
    private PagingRepository<Integer, Rental> rentalRepository;

    private Pageable pageableObj = new PageableImpl(0, 1);


    /**
     * Constructor for ClientRentalService.
     * @param crs a {@code Repository} instance for Clients repository.
     * @param mov a {@code Repository} instance for Movies repository.
     */
    public ClientRentalService(PagingRepository<Integer,Client> crs, PagingRepository<Integer,Movie> mov, PagingRepository<Integer, Rental> rent)
    {
        clientRepository = crs;
        movieRepository = mov;
        rentalRepository = rent;
    }

    /**
     * Adds a client to Clients repository.
     * @param client the {@code Client} to be added.
     * @throws ValidatorException if the client is not valid.
     */
    public void addClient(Client client) throws ValidatorException {
        clientRepository.save(client).ifPresent(c -> {throw new RuntimeException("Client already exists");});
    }

    /**
     * Adds a movie to Movies repository.
     * @param movie the {@code Movie} to be added.
     * @throws ValidatorException if the movie is not valid.
     */
    public void addMovie(Movie movie) throws ValidatorException {
        movieRepository.save(movie).ifPresent(c -> {throw new RuntimeException("Movie already exists");});
    }

    public void addRental(Rental rental) throws ValidatorException{

        clientRepository.findOne(rental.getClientId()).orElseThrow(() -> new ClientNotFoundException("Client ID was not found."));
        Movie movie = movieRepository.findOne(rental.getMovieId()).orElseThrow(() -> new MovieNotFoundException("Movie ID was not found."));
        if (movie.isRented()) throw new MovieAlreadyRentedException(movie.getMovieName() + "is already rented.");
        movie.setRented(true);
        rentalRepository.save(rental);
    }

    public Optional<Rental> deleteRental(Integer id) throws ValidatorException{
        Rental rental = rentalRepository.findOne(id).orElseThrow(() -> new RentalNotFoundException("Rental ID was not found."));
        int mId = rental.getMovieId();
        Movie movie = movieRepository.findOne(mId).get();
        movie.setRented(false);
        return rentalRepository.delete(id);
    }

    /**
     * Updates a client in Clients repository.
     * @param client the {@code Client} to be updated.
     * @throws ValidatorException if the client is not valid.
     */
    public void updateClient(Client client) throws ValidatorException {
        clientRepository.findOne(client.getId()).orElseThrow(() -> new ClientNotFoundException("Client ID was not found."));
        clientRepository.update(client);
    }

    /**
     * Updates a movie in Movies repository.
     * @param movie the {@code Movie} to be updated.
     * @throws ValidatorException if the movie is not valid.
     */
    public void updateMovie(Movie movie) throws ValidatorException {
        movieRepository.findOne(movie.getId()).orElseThrow(() -> new MovieNotFoundException("Movie ID was not found."));
        movieRepository.update(movie);
    }

    /**
     * Deletes a Client from clients repository.
     * @param id an {@code Integer} representing the id of the Client to be deleted.
     * @return an {@code Optional} - null if there is no Client with the given id; the Movie otherwise.
     */
    public Optional<Client> deleteClient(Integer id) {
        clientRepository.findOne(id).orElseThrow(() -> new ClientNotFoundException("Client ID was not found."));

        return clientRepository.delete(id);
    }

    /**
     * Deletes a Movie from movies repository.
     * @param id an {@code Integer} representing the id of the movie to be deleted.
     * @return an {@code Optional} - null if there is no Movie with the given id; the Movie otherwise.
     */
    public Optional<Movie> deleteMovie(Integer id) {
        movieRepository.findOne(id).orElseThrow(() -> new MovieNotFoundException("Movie ID was not found."));
        return movieRepository.delete(id);
    }

    /**
     * Gets all the clients from Clients repository.
     * @return a {@code Set} of all clients.
     */
    public Set<Client> getAllClients() {
        Iterable<Client> clients = clientRepository.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Client> getNextClients() {
        Page<Client> clientsPage = clientRepository.findAll(pageableObj);
        Set<Client> clients = clientsPage.getContent().collect(Collectors.toSet());
        pageableObj = clientsPage.nextPageable();
        return clients;
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
    public Set<Movie> getAllMovies()
    {
        Iterable<Movie> movies = movieRepository.findAll();
        return StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toSet());
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

    public Set<Movie> getNextMovies() {
        Page<Movie> moviesPage = movieRepository.findAll(pageableObj);
        Set<Movie> movies = moviesPage.getContent().collect(Collectors.toSet());
        pageableObj = moviesPage.nextPageable();
        return movies;
    }
    public Set<Rental> getAllRentals()
    {
        Iterable<Rental> rentals = rentalRepository.findAll();
        return StreamSupport.stream(rentals.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Rental> getNextRentals() {
        Page<Rental> rentalsPage = rentalRepository.findAll(pageableObj);
        Set<Rental> rentals = rentalsPage.getContent().collect(Collectors.toSet());
        pageableObj = rentalsPage.nextPageable();
        return rentals;
    }

    public Map moviesEachClient() {
        /* for each movie in the movieRepo, check how many record are with that id in rentals
         * count and map
         */
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .collect(Collectors.toMap(client -> client.getFirstName() + " " + client.getLastName(), client -> StreamSupport
                        .stream(rentalRepository.findAll().spliterator(), false)
                        .filter(r -> r.getClientId() == client.getId()).count())
                        );
    }

    public void setPageSize(int size) {
        pageableObj = new PageableImpl(0, size);
    }
}


