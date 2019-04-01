package movie.rental.client.service;

import movie.rental.client.tcp.TCPClient;
import movie.rental.common.HelloService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ClientRentalService implements HelloService {

    private ExecutorService executorService;
    private TCPClient tcpClient;

    public ClientRentalService(ExecutorService executorService, TCPClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<String> sayHello(String name) {
        return null;
    }

    @Override
    public Future<String> sayBye(String name) {
        return null;
    }

    @Override
    public Future<String> getNextClients() {
        return null;
    }

    @Override
    public Future<String> getNextMovies() {
        return null;
    }

    @Override
    public Future<String> getNextRentals() {
        return null;
    }

    public void setPageSize(int size) {
        return;
    }
}
