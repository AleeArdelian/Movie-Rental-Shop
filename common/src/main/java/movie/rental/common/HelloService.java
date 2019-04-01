package movie.rental.common;

import java.util.concurrent.Future;

/**
 * author: radu
 */
public interface HelloService {
    String SERVER_HOST = "localhost";
    int SERVER_PORT = 1234;

    String SAY_HELLO = "sayHello";
    Future<String> sayHello(String name);

    String SAY_BYE = "sayBye";
    Future<String> sayBye(String name);

    String GET_CLIENTS = "getNextClients";
    Future<String> getNextClients();

    String GET_MOVIES = "getNextMovies";
    Future<String> getNextMovies();

    String GET_RENTALS = "getNextRentals";
    Future<String> getNextRentals();

    String SET_PAGE_SIZE = "setPageSize";
    void setPageSize(int size);

}
