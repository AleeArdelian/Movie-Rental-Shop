package movie.rental.server;

import movie.rental.common.RentalService;
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
import movie.rental.server.repository.Repository;
import movie.rental.server.repository.file.ClientFileRepository;
import movie.rental.server.repository.mem.InMemoryRepository;
import movie.rental.server.service.Service;
import movie.rental.server.service.ServerRentalService;
import movie.rental.server.tcp.TcpServer;

import java.util.List;
import java.util.Set;
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

        TcpServer tcpServer = new TcpServer(executorService, RentalService.SERVER_PORT);

        Validator<Client> clientValidator = new ClientValidator();
        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Rental> rentalValidator = new RentalValidator();

        ClientDBRepository clientRepository = new ClientDBRepository(clientValidator);
        MovieDBRepository movieRepository = new MovieDBRepository(movieValidator);
        RentalDBRepository rentalRepository = new RentalDBRepository(rentalValidator);

        //Repository<Integer, Client> clientRepository = new ClientFileRepository(clientValidator, "data/clients");

        Service crs = new Service(clientRepository, movieRepository, rentalRepository);

        RentalService rentalService = new ServerRentalService(executorService, crs);

        tcpServer.addHandler(RentalService.SET_PAGE_SIZE, (request) -> {
            Integer size = (Integer)request.getBody();
            rentalService.setPageSize(size);
            return new Message(Message.OK);
        });

        tcpServer.addHandler(RentalService.ADD_CLIENT, (request) -> {
            Client client = (Client)request.getBody();
            try {
                rentalService.addClient(client);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.UPDATE_CLIENT, (request) -> {
            Client client = (Client)request.getBody();
            try {
                rentalService.updateClient(client);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.DELETE_CLIENT, (request) -> {
            Integer id = (Integer)request.getBody();
            try {
                rentalService.deleteClient(id);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_CLIENTS, (request) -> {
            try {
                Future<Set<Client>> clients = rentalService.getNextClients();
                return new Message(Message.OK, clients.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_ALL_CLIENTS, (request) -> {
            try {
                Future<Set<Client>> clients = rentalService.getAllClients();
                return new Message(Message.OK, clients.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(RentalService.GET_SORTED_CLIENTS, (request) -> {
            try {
                Future<List<Client>> clients = rentalService.getAllSortedClients();
                return new Message(Message.OK, clients.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.start();
    }

}
