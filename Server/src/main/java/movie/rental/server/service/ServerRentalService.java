package movie.rental.server.service;

import movie.rental.common.HelloService;
import movie.rental.common.domain.Client;
import movie.rental.common.domain.Movie;
import movie.rental.common.domain.Rental;
import movie.rental.server.repository.paging.Pageable;
import movie.rental.server.repository.paging.impl.PageableImpl;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServerRentalService implements HelloService {

    private ExecutorService executorService;
    private ClientRentalService rentalService;

    private Pageable pageableObj = new PageableImpl(0, 1);

    public ServerRentalService(ExecutorService executorService, ClientRentalService rentalService) {
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

    @Override
    public void setPageSize(int size) {
        pageableObj = new PageableImpl(0, size);
    }
}
