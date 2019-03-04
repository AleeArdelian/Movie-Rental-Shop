package repository;

import domain.Client;
import domain.Movie;
import domain.validators.ClientValidator;
import domain.validators.MovieValidator;
import domain.validators.Validator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InMemoryRepositoryTest {
    private static final Validator<Client> clientValidator = new ClientValidator();
    private Repository<Integer, Client> clientRepository = new InMemoryRepository<>(clientValidator);
    private static final Validator<Movie> movieValidator = new MovieValidator();
    private Repository<Integer, Movie> movieRepository =new InMemoryRepository<>(movieValidator);

    private Client client;
    private Movie movie;

    @Before
    public void setUp(){
        client = new Client("Daraban","Ana",21);
        client.setId(4);
        movie = new Movie("Shutter Island",2010,"Martin Scorsese");
        movie.setId(4);
    }
    @Test
    public void findOne() {

    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }
}