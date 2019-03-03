package service;

import domain.Client;
import domain.Movie;
import repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientRentalService {

    private Repository<Integer, Client> clientRepository;
    private Repository<Integer, Movie> movieRepository;

    public ClientRentalService(Repository<Integer,Client> crs, Repository<Integer,Movie> mov)
    {
        clientRepository = crs;
        movieRepository=mov;
    }
    public void addClient(Client client)
    {
        clientRepository.save(client);
    }

    public void addMovie(Movie movie)
    {
        movieRepository.save(movie);
    }

    public Set<Client> getAllClients()
    {
        Iterable<Client> clients = clientRepository.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Movie> getAllMovies()
    {
        Iterable<Movie> movies = movieRepository.findAll();
        return StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toSet());
    }
}
