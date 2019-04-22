package com.shop.core.model.validators;

import com.shop.core.model.Movie;

import java.util.Calendar;

/**
 * MovieValidator class for validating data about a movie.
 * Implements Validator interface.
 */
public class MovieValidator implements Validator<Movie> {

    /**
     * Method for validating information about a movie.
     * A Movie is valid if: - its id is not negative
     *                      - its year of release is greater than current year and smaller than the year of release
     *                      of the first movie ever made (1888)
     * @param movie a {@code Movie} instance to be validated.
     * @throws ValidatorException if data about the movie is not valid.
     */

    @Override
    public void validate(Movie movie) throws ValidatorException {
        String exceptions = "Invalid movie: ";
        if (movie.getId() < 0)
            exceptions += "Id must not be a negative number; ";
        if (movie.getYear() < 1888 || movie.getYear() > Calendar.getInstance().get(Calendar.YEAR))
            exceptions += "Invalid year of production; ";
        if (!exceptions.equals("Invalid movie: "))
            throw new ValidatorException(exceptions);

    }

}
