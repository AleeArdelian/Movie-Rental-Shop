import movie.rental.client.service.ClientRentalService;
import movie.rental.client.tcp.TCPClient;
import movie.rental.client.ui.AbstractMenu;
import movie.rental.client.ui.MainMenu;
import movie.rental.common.RentalService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TCPClient tcpClient = new TCPClient(RentalService.SERVER_HOST, RentalService.SERVER_PORT);
        RentalService crs = new ClientRentalService(executorService, tcpClient);
        AbstractMenu ui = new MainMenu(crs);
        ui.run();
        executorService.shutdownNow();
        System.out.println("DISCONNECTED");
    }

}
