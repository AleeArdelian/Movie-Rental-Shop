package movie.rental.server.service;

import movie.rental.common.RentalService;
import movie.rental.common.domain.Client;

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
