package movie.rental.server;

import movie.rental.common.HelloService;
import movie.rental.common.Message;
import movie.rental.common.domain.Client;
import movie.rental.common.domain.Movie;
import movie.rental.common.domain.Rental;
import movie.rental.common.domain.validators.Validator;
import movie.rental.server.domain.validators.ClientValidator;
import movie.rental.server.domain.validators.MovieValidator;
import movie.rental.server.domain.validators.RentalValidator;
import movie.rental.server.repository.BD.ClientDBRepository;
import movie.rental.server.repository.BD.MovieDBRepository;
import movie.rental.server.repository.BD.RentalDBRepository;
import movie.rental.server.service.ClientRentalService;
import movie.rental.server.service.ServerRentalService;
import movie.rental.server.tcp.TcpServer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * author: radu
 */
public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());

        TcpServer tcpServer = new TcpServer(executorService,
                                            HelloService.SERVER_PORT);

        Validator<Client> clientValidator = new ClientValidator();
        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Rental> rentalValidator = new RentalValidator();

        ClientDBRepository clientRepository = new ClientDBRepository(clientValidator);
        MovieDBRepository movieRepository = new MovieDBRepository(movieValidator);
        RentalDBRepository rentalRepository = new RentalDBRepository(rentalValidator);

        ClientRentalService crs = new ClientRentalService(clientRepository, movieRepository, rentalRepository);

        HelloService helloService = new ServerRentalService(executorService, crs);


        tcpServer.addHandler(
                HelloService.SAY_HELLO, (request) -> {
                    String name = request.getBody();
                    Future<String> result =
                            helloService.sayHello(name);
                    try {
                        //compute response
//                        return new Message(Message.OK, result.get());
                        return getMessage(Message.OK, result.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
//                        return new Message(Message.ERROR, e.getMessage());
                        return getMessage(Message.ERROR, e.getMessage());
                    }
                });

        tcpServer.addHandler(
                HelloService.SAY_BYE, (request) -> {
                    String name = request.getBody();
                    Future<String> result =
                            helloService.sayBye(name);
                    try {
                        //compute response
                        return getMessage(Message.OK, result.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return getMessage(Message.ERROR, e.getMessage());
                    }
                });
        tcpServer.addHandler(
                HelloService.GET_CLIENTS, (request) -> {
                    String name = request.getBody();
                    Future<String> result =
                            helloService.getNextClients();
                    try{
                        return getMessage(Message.OK , result.get());
                    }catch (InterruptedException | ExecutionException e){
                        return getMessage(Message.ERROR, e.getMessage());
                    }
                }
        );

        tcpServer.startServer();

        System.out.println("server - bye");
    }

    private static Message getMessage(String header, String body) {
        return Message.builder()
                      .header(header)
                      .body(body)
                      .build();
    }


}
