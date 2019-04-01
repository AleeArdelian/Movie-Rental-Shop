package movie.rental.server.service;
import movie.rental.common.HelloService;
import movie.rental.server.domain.Client;
import movie.rental.server.domain.Movie;
import movie.rental.server.domain.Rental;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class rentalService implements HelloService {

    private ExecutorService executorService;
    private ClientRentalService rentalService;

    public rentalService(ExecutorService executorService, ClientRentalService rentalService) {
        this.executorService = executorService;
        this.rentalService = rentalService;
    }

    @Override
    public Future<String> sayHello(String name) {
        return executorService.submit(() -> "Hello " + name);
    }

    @Override
    public Future<String> sayBye(String name) {
        return executorService.submit(() -> "Bye " + name);
    }

    @Override
    public Future<String> getNextClients() {
        Set<Client> all = rentalService.getNextClients();
        return executorService.submit(all::toString);
    }

    @Override
    public Future<String> getNextMovies() {
        Set<Movie> all = rentalService.getNextMovies();
        return executorService.submit(all::toString);
    }

    @Override
    public Future<String> getNextRentals() {
        Set<Rental> all = rentalService.getNextRentals();
        return executorService.submit(all::toString);
    }
}
