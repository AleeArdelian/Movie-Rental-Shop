package movie.rental.client.service;

import movie.rental.client.tcp.TCPClient;
import movie.rental.common.domain.Rental;
import movie.rental.common.domain.exceptions.RentalServiceException;
import movie.rental.common.service.RentalService;
import movie.rental.common.domain.Message;
import movie.rental.common.domain.Client;
import movie.rental.common.domain.Movie;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ClientRentalService implements RentalService {

    private ExecutorService executorService;
    private TCPClient tcpClient;

    public ClientRentalService(ExecutorService executorService, TCPClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Set<Client>> getAllClients() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(RentalService.GET_ALL_CLIENTS);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<Client>)response.getBody();
        });
    }

    @Override
    public Future<List<Client>> getAllSortedClients() {
        return executorService.submit(() -> {
            Message request = new Message(RentalService.GET_SORTED_CLIENTS);
            Message response = tcpClient.sendAndReceive(request);
            return (List<Client>)response.getBody();
        });
    }

    @Override
    public void addMovie(Movie movie) {
        executorService.execute(() -> {
            Message request = new Message(RentalService.ADD_MOVIE, movie);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public void updateMovie(Movie movie) {
        executorService.execute(() -> {
            Message request = new Message(RentalService.UPDATE_MOVIE, movie);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public void deleteMovie(Integer id) {
        executorService.execute(() -> {
            Message request = new Message(RentalService.DELETE_MOVIE, id);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public Future<Set<Movie>> getNextMovies() {
        return executorService.submit(() -> {
            Message request = new Message(RentalService.GET_MOVIES);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<Movie>)response.getBody();
        });
    }

    @Override
    public Future<Set<Movie>> getAllMovies() {
        return executorService.submit(() -> {
            Message request = new Message(RentalService.GET_ALL_MOVIES);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<Movie>)response.getBody();
        });
    }

    @Override
    public Future<List<Movie>> getAllSortedMovies() {
        return executorService.submit(() -> {
            Message request = new Message(RentalService.GET_SORTED_MOVIES);
            Message response = tcpClient.sendAndReceive(request);
            return (List<Movie>)response.getBody();
        });
    }

    @Override
    public void addRental(Rental rental) {
        executorService.execute(() -> {
            Message request = new Message(RentalService.ADD_RENTAL, rental);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public void deleteRental(Integer id) {
        executorService.execute(() -> {
            Message request = new Message(RentalService.DELETE_RENTAL, id);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public Future<Set<Rental>> getNextRentals() {
        return executorService.submit(() -> {
            Message request = new Message(RentalService.GET_RENTALS);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<Rental>)response.getBody();
        });
    }

    @Override
    public Future<Set<Rental>> getAllRentals() {
        return executorService.submit(() -> {
            Message request = new Message(RentalService.GET_ALL_RENTALS);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<Rental>)response.getBody();
        });
    }

    public void setPageSize(Integer size) {
        executorService.execute(() -> {
            Message request = new Message(RentalService.SET_PAGE_SIZE, size);
            tcpClient.sendAndReceive(request);
        });
    }

    public CompletableFuture<Boolean> addClient(Client client) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(RentalService.ADD_CLIENT, client);
            Message response = tcpClient.sendAndReceive(request);
            if (response.getHeader().equals("ERROR"))
                throw new RentalServiceException("Error");
            else return true;
        }, executorService);
    }

    public void updateClient(Client client) {
        executorService.execute(() -> {
            Message request = new Message(RentalService.UPDATE_CLIENT, client);
            tcpClient.sendAndReceive(request);
        });
    }

    public void deleteClient(Integer id) {
        executorService.execute(() -> {
            Message request = new Message(RentalService.DELETE_CLIENT, id);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public CompletableFuture<Set<Client>> getNextClients() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(RentalService.GET_CLIENTS);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<Client>)response.getBody();
        }, executorService);
    }

}
