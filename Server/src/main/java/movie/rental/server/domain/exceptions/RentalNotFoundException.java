package movie.rental.server.domain.exceptions;

public class RentalNotFoundException extends RuntimeException{
    public RentalNotFoundException(String msg){super(msg);}
}
