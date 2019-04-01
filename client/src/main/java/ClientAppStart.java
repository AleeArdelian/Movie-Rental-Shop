import movie.rental.client.service.ClientRentalService;
import movie.rental.client.tcp.TCPClient;
import movie.rental.client.ui.UI;
import movie.rental.common.HelloService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientAppStart {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TCPClient tcpClient = new TCPClient(HelloService.SERVER_HOST, HelloService.SERVER_PORT);
        HelloService crs = new ClientRentalService(executorService, tcpClient);
        UI ui = new UI(crs);
        try {
            ui.runMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
        System.out.println("DISCONNECTED");
    }

}
