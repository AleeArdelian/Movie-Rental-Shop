package ro.mpp.movie.core.model.exceptions;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String msg) {
        super(msg);
    }
}
