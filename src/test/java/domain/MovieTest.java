package domain;

import domain.validators.ValidatorException;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class MovieTest {

    private static final Integer id = -5;
    private static final String name = "Shutter Island";
    private static final Integer year = 2010;
    private static final String director = "Martin Scorsese";

    private Movie movie;

    @Before
    public void setUp() throws ValidatorException {
        movie = new Movie(name, year, director);
        movie.setId(id);
    }

    @Test
    public void getMovieName() {
        assertEquals("Shutter Island", movie.getMovieName());
    }

    @Test
    public void getYear() {
        assertEquals(year, movie.getYear());
    }

    @Test
    public void getMovieDirector() {
        assertEquals("Martin Scorsese", movie.getMovieDirector());
    }

}