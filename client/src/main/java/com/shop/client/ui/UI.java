package com.shop.client.ui;

import com.shop.core.model.Client;
import com.shop.core.model.Movie;
import com.shop.core.model.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.shop.core.service.ClientService;

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
    ClientService crs;

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
    private void printAllClients() {
        Set<Client> clients = crs.getAllClients();
        clients.forEach(System.out::println);
    }

    /**
     * Method that prints all the movies in the console.
     */
    private void printAllMovies() {
        Set<Movie> movies = crs.getAllMovies();
        movies.forEach(System.out::println);
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

        Client client = new Client(firstName, lastName, age);
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

        Movie movie = new Movie(name, year, director);
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
                    crs.addClient(getClientFrom(keyboard));
                    break;
                case 2:
                    System.out.println("Remove a new client: "); Scanner scanner = new Scanner(System.in);
                    System.out.println("ID: ");
                    Integer ID = scanner.nextInt();
                    crs.deleteClient(ID);
                    break;
                case 3:
                    System.out.println("Update client: ");
                    crs.updateClient(getClientFrom(keyboard));
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
                    crs.addMovie(getMovieFrom(keyboard));
                    break;
                case 2:
                    System.out.println("Remove a movie: ");
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("ID: ");
                    Integer ID = scanner.nextInt();
                    crs.deleteMovie(ID);
                    break;
                case 3:
                    System.out.println("Update a movie: ");
                    crs.updateMovie(getMovieFrom(keyboard));
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