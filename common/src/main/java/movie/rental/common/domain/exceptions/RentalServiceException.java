package movie.rental.common.domain.exceptions;

/**
 * author: radu
 */
public class RentalServiceException extends RuntimeException {
    public RentalServiceException(String message) {
        super(message);
    }

    public RentalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RentalServiceException(Throwable cause) {
        super(cause);
    }
}
