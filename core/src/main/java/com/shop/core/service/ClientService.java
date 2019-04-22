package com.shop.core.service;

import com.shop.core.model.Client;
import com.shop.core.model.Movie;

import java.util.Set;

public interface ClientService {
    void addClient(Client client);
    void addMovie(Movie movie);
    void updateClient(Client client);
    void updateMovie(Movie movie);
    void deleteClient(Integer id);
    void deleteMovie(Integer id);
    Set<Client> getAllClients();
    Set<Movie> getAllMovies();

}
