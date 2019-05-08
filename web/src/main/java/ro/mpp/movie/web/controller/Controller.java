package ro.mpp.movie.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mpp.movie.core.model.Client;
import ro.mpp.movie.core.model.Movie;
import ro.mpp.movie.core.model.Rental;
import ro.mpp.movie.core.service.ClientService;
import ro.mpp.movie.web.converter.ClientConverter;
import ro.mpp.movie.web.converter.MovieConverter;

import ro.mpp.movie.web.converter.RentalConverter;
import ro.mpp.movie.web.dto.*;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private RentalConverter rentalConverter;


    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public List<ClientDto> getAllClients() {
        log.trace("getAllClients --- method entered");

        List<Client> clients = clientService.getAllClients();

        log.trace("getAllClients: result={}", clients);
        return new ArrayList<>(clientConverter.convertModelsToDtos(clients));
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDto addClient(@RequestBody ClientDto dto) {
        log.trace("addClient: dto={}", dto);
        Client saved = this.clientService.addClient(clientConverter.convertDtoToModel(dto));
        ClientDto result = clientConverter.convertModelToDto(saved);
        log.trace("addClientt: result={}", result);
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
    public List<MovieDto> getAllMovies() {
        log.trace("getAllMovies --- method entered");

        List<Movie> movies = clientService.getAllMovies();

        log.trace("getAllMovies: result={}", movies);
        return new ArrayList<>(movieConverter.convertModelsToDtos(movies));
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

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public List<RentalDto> getAllRentals() {
        log.trace("getAllRentals--- method entered");

        List<Rental> rentals = clientService.getAllRentals();

        log.trace("getAllRentals: result={}", rentals);
        return new ArrayList<>(rentalConverter.convertModelsToDtos(rentals));
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    RentalDto addRental(@RequestBody RentalDto dto) {
        log.trace("addRental: dto={}", dto);
        Rental saved = this.clientService.addRental(rentalConverter.convertDtoToModel(dto));
        RentalDto result = rentalConverter.convertModelToDto(saved);
        log.trace("addRental: result={}", result);
        return result;
    }


    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteRental(@PathVariable Integer id) {
        log.trace("deleteRental: id={}", id);
        clientService.deleteRental(id);
        log.trace("deleteRental --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
