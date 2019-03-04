package domain.validators;

import domain.Movie;

import java.util.Calendar;

public class MovieValidator implements Validator<Movie> {

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
