package ro.mpp.movie.client.ui;

import ro.mpp.movie.core.model.Client;
import ro.mpp.movie.core.model.Movie;
import ro.mpp.movie.core.model.validators.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ro.mpp.movie.web.dto.ClientDto;
import ro.mpp.movie.web.dto.ClientsDto;
import ro.mpp.movie.web.dto.MovieDto;
import ro.mpp.movie.web.dto.MoviesDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;

/**
 * User Interface class
 * MainMenu-based console application
 */
@Component
public class UI {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Method that prints the Main MainMenu in the console.
     */
    private static void showMainMenu() {
        System.out.println("\t\tMovie Rental Shop\n\n" +
                "  1. Clients\n" +
                "  2. Movies\n" +
                "  3. Rentals\n" +
                "  0. Exit\n");
    }

    /**
     * Method that prints the Clients MainMenu in the console.
     */
    private static void showClientsMenu() {
        System.out.println("\t\tCLIENTS\n\n" +
                "  1. Add\n" +
                "  2. Delete\n" +
                "  3. Update\n" +
                "  4. List all\n" +
                "  0. Back\n");
    }

    /**
     * Method that prints the Movies MainMenu in the console.
     */
    private static void showMoviesMenu() {
        System.out.println("\t\tMOVIES\n\n" +
                "  1. Add\n" +
                "  2. Delete\n" +
                "  3. Update\n" +
                "  4. List all\n" +
                "  0. Back\n");
    }

    /**
     * Method that print the Rentals MainMenu in the console.
     */
    private static void showRentalsMenu() {
        System.out.println("\t\tMOVIES\n\n" +
                "  1. Rent a movie\n" +
                "  2. Return a movie\n" +
                "  0. Back\n");
    }

    /**
     * Method that reads the user choice from the keyboard.
     * @param keyboard a {@code BufferedReader} instance use to read from System.in.
     * @return an {@code int} that represent the user choice (the number of the menu line).
     * @throws IOException if there are problems with getting the input from System.in.
     */
    private int readUserChoice(BufferedReader keyboard) throws IOException {
        System.out.print("Your choice: ");
        String userChoice = keyboard.readLine();
        System.out.println();
        return Integer.parseInt(userChoice);
    }

    /**
     * Method that prints all the clients in the console.
     */
    private void printAllClients(ClientsDto csDto) {
        Set<ClientDto> clients = csDto.getClients();
        clients.forEach(c -> System.out.println(c.getId() + " " + c.getLastName() + " "
                + c.getFirstName() + " " + c.getAge()));
    }

    /**
     * Method that prints all the movies in the console.
     */
    private void printAllMovies(MoviesDto msDto) {
        Set<MovieDto> movies = msDto.getMovies();
        movies.forEach(m -> System.out.println(m.getId() + " " + m.getMovieName() + " "
                + m.getYearOfRelease() + " " + m.getDirector()));
    }

    /**
     * Method that reads data related to a client from keyboard: Id, Name and Age.
     * @param keyboard a {@code BufferedReader} instance use to read from System.in.
     * @return a {@code Client} instance created with the data read from keyboard.
     * @throws IOException if there are problems with getting the input from System.in.
     */
    private Client getClientFrom(BufferedReader keyboard) throws IOException
    {
        System.out.print("Id: ");
        int id = Integer.parseInt(keyboard.readLine());
        System.out.print("First name: ");
        String firstName = keyboard.readLine();
        System.out.print("Last name: ");
        String lastName = keyboard.readLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(keyboard.readLine());

        Client client = Client.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();
        client.setId(id);
        return client;
    }

    /**
     * Method that reads data related to a movie from keyboard: Id, Name, Year of release and Director.
     * @param keyboard a {@code BufferedReader} instance use to read from System.in.
     * @return a {@code Movie} instance created with the data read from keyboard.
     * @throws IOException if there are problems with getting the input from System.in.
     */
    private Movie getMovieFrom(BufferedReader keyboard) throws IOException {
        System.out.print("Id: ");
        int id = Integer.parseInt(keyboard.readLine());
        System.out.print("Name: ");
        String name = keyboard.readLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(keyboard.readLine());
        System.out.print("Director: ");
        String director = keyboard.readLine();

        Movie movie = Movie.builder()
                .movieName(name)
                .yearOfRelease(year)
                .director(director)
                .build();
        movie.setId(id);
        return movie;
    }

    /**
     * Method that runs the Clients MainMenu.
     * Prints the Clients MainMenu and loops until the user chooses to go back.
     * @param keyboard a {@code BufferedReader} instance use to read from System.in.
     * @throws IOException if there are problems with getting the input from System.in.
     */
    private void runClientsMenu(BufferedReader keyboard) throws IOException {
        boolean running = true;
        while (running) {
            showClientsMenu();
            int userChoice = readUserChoice(keyboard);
            switch (userChoice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    System.out.println("Add a new client: ");
                    ClientDto saved = restTemplate.postForObject("http://localhost:8080/movie-shop/api/clients",
                            getClientFrom(keyboard),
                            ClientDto.class);
                    System.out.println("Successfully saved " + saved);
                    break;
                case 2:
                    System.out.println("Remove a client: "); Scanner scanner = new Scanner(System.in);
                    System.out.println("ID: ");
                    int id = scanner.nextInt();
                    restTemplate.delete("http://localhost:8080/movie-shop/api/clients/{id}", id);
                    break;
                case 3:
                    System.out.println("Update client: ");
                    Client client = getClientFrom(keyboard);
                    restTemplate.put("http://localhost:8080/movie-shop/api/clients/{id}",
                            client,
                            client.getId());
                    System.out.println("Successfully updated");
                    break;
                case 4:
                    ClientsDto csDto = restTemplate.getForObject("http://localhost:8080/movie-shop/api/clients",
                            ClientsDto.class);
                    printAllClients(csDto);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    /**
     * Method that runs the Movies MainMenu.
     * Prints the Movies MainMenu and loops until the user chooses to go back.
     * @param keyboard a {@code BufferedReader} instance use to read from System.in.
     * @throws IOException if there are problems with getting the input from System.in.
     */
    private void runMoviesMenu(BufferedReader keyboard) throws IOException {
        boolean running = true;
        while (running) {
            showMoviesMenu();
            int userChoice = readUserChoice(keyboard);
            switch (userChoice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    System.out.println("Add a new movie: ");
                    MovieDto saved = restTemplate.postForObject("http://localhost:8080/movie-shop/api/movies",
                            getMovieFrom(keyboard),
                            MovieDto.class);
                    System.out.println("Successfully saved " + saved);
                    break;
                case 2:
                    System.out.println("Remove a movie: ");
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("ID: ");
                    int id = scanner.nextInt();
                    restTemplate.delete("http://localhost:8080/movie-shop/api/movies/{id}", id);
                    break;
                case 3:
                    System.out.println("Update a movie: ");
                    Movie movie = getMovieFrom(keyboard);
                    restTemplate.put("http://localhost:8080/movie-shop/api/movies/{id}",
                            movie,
                            movie.getId());
                    System.out.println("Successfully updated");
                    break;
                case 4:
                    MoviesDto msDto = restTemplate.getForObject("http://localhost:8080/movie-shop/api/movies",
                            MoviesDto.class);
                    printAllMovies(msDto);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }


    /**
     * Method that runs the Rentals MainMenu.
     * Prints the Rentals MainMenu and loops until the user chooses to go back.
     * @param keyboard a {@code BufferedReader} instance use to read from System.in.
     * @throws IOException if there are problems with getting the input from System.in.
     */
    private void runRentalsMenu(BufferedReader keyboard) throws IOException {
        boolean running = true;
        while (running) {
            showRentalsMenu();
            int userChoice = readUserChoice(keyboard);
            switch (userChoice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    System.out.println("Feature soon to be implemented :) ");
                    break;
                case 2:
                    System.out.println("Feature soon to be implemented :) ");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    /**
     * Method that runs the Main MainMenu.
     * Prints the Main MainMenu and loops until the user chooses to exit the application.
     * @throws IOException if there are problems with getting the input from System.in.
     */
    public void runMainMenu() {
        boolean running = true;
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        while (running) {
            try {
                showMainMenu();
                int userChoice = readUserChoice(keyboard);
                switch (userChoice) {
                    case 0:
                        running = false;
                        break;
                    case 1:
                        runClientsMenu(keyboard);
                        break;
                    case 2:
                        runMoviesMenu(keyboard);
                        break;
                    case 3:
                        runRentalsMenu(keyboard);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again!");
                        break;
                }
            } catch (ValidatorException val) {
                System.out.println(val.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
