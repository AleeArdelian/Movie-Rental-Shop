package movie.rental.server.service;

import movie.rental.common.domain.Rental;
import movie.rental.common.service.RentalService;
import movie.rental.common.domain.Client;
import movie.rental.common.domain.Movie;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServerRentalService implements RentalService {

    private ExecutorService executorService;
    private Service crs;

    public ServerRentalService(ExecutorService executorService, Service crs) {
        this.executorService = executorService;
        this.crs = crs;
    }

    @Override
    public Future<Set<Client>> getAllClients() {
        return executorService.submit(() -> crs.getAllClients());
    }

    @Override
    public Future<List<Client>> getAllSortedClients() {
        return executorService.submit(() -> crs.getAllSortedClients());
    }

    @Override
    public void addMovie(Movie movie) {
        executorService.execute(() -> crs.addMovie(movie));
    }

    @Override
    public void updateMovie(Movie movie) {
        executorService.execute(() -> crs.updateMovie(movie));
    }

    @Override
    public void deleteMovie(Integer id) {
        executorService.execute(() -> crs.deleteMovie(id));
    }

    @Override
    public Future<Set<Movie>> getNextMovies() {
        return executorService.submit(() -> crs.getNextMovies());
    }

    @Override
    public Future<Set<Movie>> getAllMovies() {
        return executorService.submit(() -> crs.getAllMovies());
    }

    @Override
    public Future<List<Movie>> getAllSortedMovies() {
        return executorService.submit(() -> crs.getAllSortedMovies());
    }

    @Override
    public void addRental(Rental rental) {
        executorService.execute(() -> crs.addRental(rental));
    }

    @Override
    public void deleteRental(Integer id) {
        executorService.execute(() -> crs.deleteRental(id));
    }

    @Override
    public Future<Set<Rental>> getNextRentals() {
        return executorService.submit(() -> crs.getNextRentals());
    }

    @Override
    public Future<Set<Rental>> getAllRentals() {
        return executorService.submit(() -> crs.getAllRentals());
    }

    @Override
    public void setPageSize(Integer size) {
        executorService.execute(() -> crs.setPageSize(size));
    }

    @Override
    public void addClient(Client client) {
        executorService.execute(() -> crs.addClient(client));
    }

    @Override
    public void updateClient(Client client) {
        executorService.execute(() -> crs.updateClient(client));
    }

    @Override
    public void deleteClient(Integer id) {
        executorService.execute(() -> crs.deleteClient(id));
    }

    @Override
    public Future<Set<Client>> getNextClients() {
        return executorService.submit(() -> crs.getNextClients());
    }


}
