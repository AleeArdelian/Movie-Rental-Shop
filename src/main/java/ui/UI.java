package ui;

import domain.Client;
import domain.Movie;
import domain.validators.ValidatorException;
import service.ClientRentalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;

public class UI {

    private ClientRentalService crs;

    public UI(ClientRentalService crs) {
        this.crs = crs;
    }

    private static void showMainMenu() {
        System.out.println("\n\t\tMovie Rental Shop\n\n" +
                "  1. Clients\n" +
                "  2. Movies\n" +
                "  3. Rentals\n" +
                "  0. Exit\n");
    }

    private static void showClientsMenu() {
        System.out.println("\n\t\tCLIENTS\n\n" +
                "  1. Add\n" +
                "  2. Delete\n" +
                "  3. Update\n" +
                "  4. List all\n" +
                "  0. Back\n");
    }

    private static void showMoviesMenu() {
        System.out.println("\n\t\tMOVIES\n\n" +
                "  1. Add\n" +
                "  2. Delete\n" +
                "  3. Update\n" +
                "  4. List all\n" +
                "  0. Back\n");
    }

    private static void showRentalsMenu() {
        System.out.println("\n\t\tMOVIES\n\n" +
                "  1. Rent a movie\n" +
                "  2. Return a movie\n" +
                "  0. Back\n");
    }

    private int readUserChoice(BufferedReader keyboard) throws IOException {
        System.out.print("Your choice: ");
        String userChoice = keyboard.readLine();
        return Integer.parseInt(userChoice);
    }

    private void printAllClients() {
        Set<Client> clients = crs.getAllClients();
        clients.forEach(System.out::println);
    }

    private void printAllMovies() {
        Set<Movie> movies = crs.getAllMovies();
        movies.forEach(System.out::println);
    }

    private Client getClientFrom(BufferedReader keyboard) throws ValidatorException, IOException
    {
        System.out.print("Id: ");
        int id = Integer.parseInt(keyboard.readLine());
        System.out.print("First name: ");
        String firstName = keyboard.readLine();
        System.out.print("Last name: ");
        String lastName = keyboard.readLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(keyboard.readLine());

        Client client = new Client(firstName, lastName, age);
        client.setId(id);

        return client;
    }

    private Movie getMovieFrom(BufferedReader keyboard) throws ValidatorException, IOException
    {
        System.out.print("Id: ");
        int id = Integer.parseInt(keyboard.readLine());
        System.out.print("Name: ");
        String name = keyboard.readLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(keyboard.readLine());
        System.out.print("Director: ");
        String director = keyboard.readLine();

        Movie movie = new Movie(name, year, director);
        movie.setId(id);

        return movie;
    }

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
                    crs.addClient(getClientFrom(keyboard));
                    break;
                case 2:
                    //
                    break;
                case 3:
                    //
                    break;
                case 4:
                    printAllClients();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

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
                    crs.addMovie(getMovieFrom(keyboard));
                    break;
                case 2:
                    //
                    break;
                case 3:
                    //
                    break;
                case 4:
                    printAllMovies();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

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
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public void start() throws IOException {
        boolean running = true;
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        //Scanner keyboard = new Scanner(System.in);
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
                        System.out.println("Invalid choice!");
                        break;

                }

            } catch (ValidatorException val) {
                System.out.println(val.getMessage());
            }
        }
    }
}
