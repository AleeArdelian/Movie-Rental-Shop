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


}
