package movie.rental.common;

import movie.rental.common.domain.Client;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * author: radu
 */
public interface RentalService {

    String SERVER_HOST = "localhost";
    int SERVER_PORT = 1234;

    String SET_PAGE_SIZE = "setPageSize";
    void setPageSize(Integer size);

    String ADD_CLIENT = "addClient";
    void addClient(Client client);

    String UPDATE_CLIENT = "updateClient";
    void updateClient(Client client);

    String DELETE_CLIENT = "deleteClient";
    void deleteClient(Integer id);

    String GET_CLIENTS = "getNextClients";
    Future<Set<Client>> getNextClients();

    String GET_ALL_CLIENTS = "getAllClients";
    Future<Set<Client>> getAllClients();

    String GET_SORTED_CLIENTS = "getAllSortedClients";
    Future<List<Client>> getAllSortedClients();
}
