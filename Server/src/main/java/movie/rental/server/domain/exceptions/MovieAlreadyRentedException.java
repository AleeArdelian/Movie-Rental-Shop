package movie.rental.server.domain.exceptions;

public class MovieAlreadyRentedException extends RuntimeException {

        public MovieAlreadyRentedException(String msg) {
            super(msg);
        }
}
