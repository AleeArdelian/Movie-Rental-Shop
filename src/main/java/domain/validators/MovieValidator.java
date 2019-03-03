package domain.validators;

import domain.Movie;

import java.util.Calendar;

public class MovieValidator implements Validator<Movie> {

    @Override
    public void validate(Movie movie) throws ValidatorException {
        String exceptions = "";
        if (movie.getYear() < 1888 && movie.getYear() > Calendar.getInstance().get(Calendar.YEAR))
            exceptions += "Invalid year of production\n";
        if (!exceptions.equals(""))
            throw new ValidatorException(exceptions);
    }

}
