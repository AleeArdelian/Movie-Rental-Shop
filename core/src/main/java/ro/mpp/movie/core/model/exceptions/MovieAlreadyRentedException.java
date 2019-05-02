package ro.mpp.movie.core.model.exceptions;

public class MovieAlreadyRentedException extends RuntimeException {

        public MovieAlreadyRentedException(String msg) {
            super(msg);
        }
}
