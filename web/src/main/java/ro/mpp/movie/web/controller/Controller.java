package ro.mpp.movie.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mpp.movie.core.model.Client;
import ro.mpp.movie.core.model.Movie;
import ro.mpp.movie.core.service.ClientService;
import ro.mpp.movie.web.converter.ClientConverter;
import ro.mpp.movie.web.converter.MovieConverter;

import ro.mpp.movie.web.dto.ClientDto;
import ro.mpp.movie.web.dto.ClientsDto;
import ro.mpp.movie.web.dto.MovieDto;
import ro.mpp.movie.web.dto.MoviesDto;

import java.util.Set;


@RestController
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    ClientsDto getAllClients() {
        //log.trace("getAllClients --- method entered");
        Set<Client> clients = clientService.getAllClients();
        Set<ClientDto> dtos = clientConverter.convertModelsToDtos(clients);
        ClientsDto result = new ClientsDto(dtos);
        //log.trace("getAllClients: result={}", result);
        return result;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDto addClient(@RequestBody ClientDto dto) {
        //log.trace("addClient: dto={}", dto);
        Client saved = this.clientService.addClient(clientConverter.convertDtoToModel(dto));
        ClientDto result = clientConverter.convertModelToDto(saved);
        //log.trace("addClientt: result={}", result);
        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Integer id, @RequestBody ClientDto dto) {
        //log.trace("updateClient: id={},dto={}", id, dto);
        Client update = clientService.updateClient(
                id,
                clientConverter.convertDtoToModel(dto));
        ClientDto result = clientConverter.convertModelToDto(update);
        //log.trace("updateClient: result={}", result);
        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        log.trace("deleteClient: id={}", id);
        clientService.deleteClient(id);
        log.trace("deleteClient --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    MoviesDto getAllMovies() {
        log.trace("getAllMovies --- method entered");
        Set<Movie> movies = clientService.getAllMovies();
        Set<MovieDto> dtos = movieConverter.convertModelsToDtos(movies);
        MoviesDto result = new MoviesDto(dtos);
        log.trace("getAllMovies: result={}", result);
        return result;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    MovieDto addMovie(@RequestBody MovieDto dto) {
        log.trace("addMovie: dto={}", dto);
        Movie saved = this.clientService.addMovie(movieConverter.convertDtoToModel(dto));
        MovieDto result = movieConverter.convertModelToDto(saved);
        log.trace("addMovie: result={}", result);
        return result;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    MovieDto updateMovie(@PathVariable Integer id, @RequestBody MovieDto dto) {
        log.trace("updateMovie: id={},dto={}", id, dto);
        Movie update = clientService.updateMovie(
                id,
                movieConverter.convertDtoToModel(dto));
        MovieDto result = movieConverter.convertModelToDto(update);
        log.trace("updateMovie: result={}", result);
        return result;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        log.trace("deleteMovie: id={}", id);
        clientService.deleteMovie(id);
        log.trace("deleteMovie --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
