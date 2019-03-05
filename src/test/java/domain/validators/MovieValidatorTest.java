package domain.validators;

import domain.Movie;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import repository.InMemoryRepository;
import repository.Repository;

/**
 * Tests for MovieValidator class.
 */
public class MovieValidatorTest {

    private static final Integer id = -5;
    private static final String name = "Shutter Island";
    private static final Integer year = 3660;
    private static final String director = "Martin Scorsese";

    private Repository<Integer, Movie> movieRepository;
    private Validator<Movie> movieValidator = new MovieValidator();
    private Movie movie;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp(){
        movieRepository = new InMemoryRepository<>(movieValidator);
        movie = new Movie(name,year,director);
        movie.setId(id);
    }

    /**
     * Test case for validate method.
     */
    @Test
    public void validate() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Invalid movie: Id must not be a negative number; Invalid year of production; ");
        movieRepository.save(movie);

    }
}