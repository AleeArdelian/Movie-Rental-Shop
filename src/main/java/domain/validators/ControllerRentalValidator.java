package domain.validators;

import domain.Client;
import domain.Movie;
import domain.Rental;
import domain.Triple;
import repository.Repository;

public class ControllerRentalValidator implements Validator<Triple<Rental, Repository<Integer, Client>, Repository<Integer, Movie>>>
{
    @Override
    public void validate(Triple<Rental, Repository<Integer, Client>, Repository<Integer, Movie>> triple) throws ValidatorException {
        Rental rental = triple.getFirst();
        Repository<Integer, Client> clientsRepository = triple.getSecond();
        Repository<Integer, Movie> movieRepository = triple.getThird();
        String exceptions = "Invalid rental: ";
        if (!clientsRepository.findOne(rental.getClientId()).isPresent()) {
            exceptions += "Client id doesn't exist; ";
        }
        if (!movieRepository.findOne(rental.getMovieId()).isPresent()) {
            exceptions += "Movie id doesn't exist; ";
        }
        else if ((movieRepository.findOne(rental.getMovieId()).get()).isRented()) {
            exceptions += "Movie already rented; ";
        }
        if (!exceptions.equals("Invalid rental: "))
            throw new ValidatorException(exceptions);
    }
}
