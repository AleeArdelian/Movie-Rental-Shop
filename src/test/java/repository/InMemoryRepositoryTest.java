package repository;

import domain.Client;
import domain.Movie;
import domain.validators.ClientValidator;
import domain.validators.MovieValidator;
import domain.validators.Validator;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import repository.mem.InMemoryRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests for InMemoryRepository class
 */
public class InMemoryRepositoryTest {

    private static final Validator<Client> clientValidator = new ClientValidator();
    private Repository<Integer, Client> clientRepository = new InMemoryRepository<>(clientValidator);
    private static final Validator<Movie> movieValidator = new MovieValidator();
    private Repository<Integer, Movie> movieRepository =new InMemoryRepository<>(movieValidator);

    private Client client;
    private Movie movie;
    private ExpectedException expectedEx;

    @Before
    public void setUp(){
        client = new Client("Daraban","Ana",21);
        client.setId(4);
        movie = new Movie("Shutter Island",2010,"Martin Scorsese");
        movie.setId(4);

    }

    /**
     * Test case for findOne method.
     */
    @Test
    public void findOne() {
        clientRepository.save(client);
        assertTrue(clientRepository.findOne(4).isPresent());
        movieRepository.save(movie);
        assertFalse(clientRepository.findOne(5).isPresent());
    }

    /**
     * Test case for findAll method.
     */
    @Test
    public void findAll() {
        Set<Client> foundEntities = new HashSet<>();
        foundEntities.add(client);
        clientRepository.save(client);
        assertEquals(foundEntities, clientRepository.findAll());
    }

    /**
     * Test case for save method.
     */
    @Test
    public void save() {
        clientRepository.save(client);
        movieRepository.save(movie);
        if (clientRepository.findOne(4).isPresent()) {
            Client foundClient = clientRepository.findOne(4).get();
            assertEquals(client.getAge(), foundClient.getAge());
            assertEquals(client.getFirstName(), foundClient.getFirstName());
            assertEquals(client.getLastName(), foundClient.getLastName());
            assertEquals(client.getAge(), foundClient.getAge());
        }
        if (movieRepository.findOne(4).isPresent()) {
            Movie foundMovie = movieRepository.findOne(4).get();
            assertEquals(movie.getYear(), foundMovie.getYear());
            assertEquals(movie.getMovieName(), foundMovie.getMovieName());
            assertEquals(movie.getMovieDirector(), foundMovie.getMovieDirector());
        }
    }

    /**
     * Test case for delete method.
     */
    @Test
    public void delete() {
    }

    /**
     * Test case for update method.
     */
    @Test
    public void update() {
        movieRepository.save(movie);
        Movie movie3 = new Movie("Shutter",2011,"Martin");
        movie3.setId(4);
        movieRepository.update(movie3);
        Movie foundMovie = movieRepository.findOne(4).get();
        assertEquals(movie3.getYear(), foundMovie.getYear());
        assertEquals(movie3.getMovieName(), foundMovie.getMovieName());
        assertEquals(movie3.getMovieDirector(), foundMovie.getMovieDirector());
    }

    /**
     * Test case for update method, throw exception case.
     */
//    @Test
//    public void update1() {
//        movieRepository.save(movie);
//        Movie movie3 = new Movie("Shutter",2011,"Martin");
//        movie3.setId(8);
//        expectedEx.expect(RuntimeException.class);
//        expectedEx.expectMessage("Invalid key!");
//        movieRepository.update(movie3);
//    }
}