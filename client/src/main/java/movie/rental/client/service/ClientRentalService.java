package movie.rental.client.service;

import movie.rental.client.tcp.TCPClient;
import movie.rental.common.RentalService;
import movie.rental.common.Message;
import movie.rental.common.domain.Client;

import java.util.List;
import java.util.Set;
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
    public Future<Set<Client>> getAllClients() {
        return executorService.submit(() -> {
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

    public void setPageSize(Integer size) {
        executorService.execute(() -> {
            Message request = new Message(RentalService.SET_PAGE_SIZE, size);
            tcpClient.sendAndReceive(request);
        });
    }

    public void addClient(Client client) {
        executorService.execute(() -> {
            Message request = new Message(RentalService.ADD_CLIENT, client);
            tcpClient.sendAndReceive(request);
        });
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
    public Future<Set<Client>> getNextClients() {
        return executorService.submit(() -> {
            Message request = new Message(RentalService.GET_CLIENTS);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<Client>)response.getBody();
        });
    }

}
