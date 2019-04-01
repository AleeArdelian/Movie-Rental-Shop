package movie.rental.server.domain.exceptions;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String msg) {
        super(msg);
    }

}
