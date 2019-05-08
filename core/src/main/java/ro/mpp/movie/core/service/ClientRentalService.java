package ro.mpp.movie.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.mpp.movie.core.model.Client;
import ro.mpp.movie.core.model.Movie;
import ro.mpp.movie.core.model.Rental;
import ro.mpp.movie.core.model.validators.ValidatorException;
import ro.mpp.movie.core.repository.ClientRepository;
import ro.mpp.movie.core.repository.MovieRepository;
import ro.mpp.movie.core.repository.RentalRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * ClientRentalService class for controlling operations on the repositories.
 */
@Service
public class ClientRentalService implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientRentalService.class);

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    RentalRepository rentalRepository;


    /**
     * Adds a client to Clients repository.
     * @param client the {@code Client} to be added.
     * @throws ValidatorException if the client is not valid.
     */
    @Override
    public Client addClient(Client client) throws ValidatorException {
        log.trace("addClient: client={}", client);
        clientRepository.save(client);//.orElseThrow(() -> new RuntimeException("Client ID already exists."));
        log.trace("addClient --- method finished");
        return client;
    }

    /**
     * Adds a movie to Movies repository.
     * @param movie the {@code Movie} to be added.
     * @throws ValidatorException if the movie is not valid.
     */
    @Override
    public Movie addMovie(Movie movie) throws ValidatorException {
        log.trace("addMovie: movie={}", movie);
        movieRepository.save(movie);//.orElseThrow(() -> new RuntimeException("Movie ID already exists."));
        log.trace("addMovie --- method finished");
        return movie;
    }

    /**
     * Updates a client in Clients repository.
     * @param client the {@code Client} to be updated.
     * @throws ValidatorException if the client is not valid.
     */
    @Override
    @Transactional
    public Client updateClient(Integer id, Client client) throws ValidatorException {
        log.trace("updateClient --- method entered");
        log.trace("updateClient: client={}", client);
        Optional<Client> foundClient = clientRepository.findById(id);
        foundClient.ifPresent(
                updateClient -> {
                    updateClient.setFirstName(client.getFirstName());
                    updateClient.setLastName(client.getLastName());
                    updateClient.setAge(client.getAge());
                }
        );
        log.trace("updateClient --- method finished");
        return foundClient.get();
    }

    /**
     * Updates a movie in Movies repository.
     * @param movie the {@code Movie} to be updated.
     * @throws ValidatorException if the movie is not valid.
     */
    @Override
    @Transactional
    public Movie updateMovie(Integer id, Movie movie) throws ValidatorException {
        log.trace("updateMovie --- method entered");
        log.trace("updateMovie: movie={}", movie);
        Optional<Movie> foundMovie = movieRepository.findById(id);
        foundMovie.ifPresent(
                updateMovie -> {
                    updateMovie.setMovieName(movie.getMovieName());
                    updateMovie.setYearOfRelease(movie.getYearOfRelease());
                    updateMovie.setDirector(movie.getDirector());
                }
        );
        log.trace("updateMovie --- method finished");
        return foundMovie.get();
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
    public List<Client> getAllClients() {
        log.trace("getAllClients --- method entered");
        List<Client> clients = clientRepository.findAll();
        //Set<Client> result = StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
        log.trace("getAllClients: result={}", clients);
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
        return StreamSupport.stream(movies.spliterator(), false).filter(m -> m.getYearOfRelease() > year).count();
    }

    /**
     * Gets all the movies from Movies repository.
     * @return a {@code Set} of all movies.
     */
    @Override
    public List<Movie> getAllMovies()
    {
        log.trace("getAllMovies --- method entered");
        List<Movie> movies = movieRepository.findAll();
//        Set<Movie> result =  StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toSet());
        log.trace("getAllClients: result={}", movies);
        return movies;
    }

    @Override
    public Rental addRental(Rental rental) {
        log.trace("addRental: rental={}", rental);
        rentalRepository.save(rental);//.orElseThrow(() -> new RuntimeException("Client ID already exists."));
        log.trace("addRental --- method finished");
        return rental;
    }

//    @Override
//    public Rental updateRental(Integer id, Rental rental) {
//        log.trace("updateRental --- method entered");
//        log.trace("updateRental: rental={}", rental);
//        Optional<Rental> foundRental = rentalRepository.findById(id);
//        foundRental.ifPresent(
//                updateRental -> {
//                    updateRental.setClientId(rental.getClientId());
//                    updateRental.setMovieId(rental.getMovieId());
//                    updateRental.setRentalDate(rental.getRentalDate());
//                    updateRental.setReturnDate(rental.getReturnDate());
//                }
//        );
//        log.trace("updateRental --- method finished");
//        return foundRental.get();
//    }
//
    @Override
    public Rental deleteRental(Integer id) {
        log.trace("delete: id={}", id);
        Optional<Rental> found = rentalRepository.findById(id);
        Rental rental = null;
        if (found.isPresent()) {
            rental = found.get();
        }
        rentalRepository.deleteById(id);//.orElseThrow(() -> new MovieNotFoundException("Movie ID was not found."));
        log.trace("deleteRental --- method finished");
        return rental;
    }

    @Override
    public List<Rental> getAllRentals() {
        log.trace("getAllRentals --- method entered");
        List<Rental> rentals = rentalRepository.findAll();
        //Set<Rental> result = StreamSupport.stream(rentals.spliterator(), false).collect(Collectors.toSet());
        log.trace("getAllRentals: result={}", rentals);
        return rentals;
    }

//    /**
//     * Gets all the movies from Movies repository sorted by Name.
//     * @return a {@code List} of all movies.
//     */
//    public List<Movie> getAllSortedMovies()
//    {
//        Iterable<Movie> movies = movieRepository.findAll();
//        return StreamSupport.stream(movies.spliterator(), false)
//                .sorted(Comparator.comparing(Movie::getMovieName)).collect(Collectors.toList());
//    }

}


