package ui;

import domain.Client;
import domain.Movie;
import service.ClientRentalService;

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
                "  3. Rent a Movie\n" +
                "  4. Return a Movie\n" +
                "  0. Exit\n");
    }

    private int readUserChoice(Scanner keyboard) {
        System.out.print("Your choice: ");
        return keyboard.nextInt();
    }
    private void printAllClients() {
        Set<Client> clients = crs.getAllClients();
        clients.forEach(System.out::println);
    }

    private void printAllMovies() {
        Set<Movie> movies = crs.getAllMovies();
        movies.forEach(System.out::println);
    }

    public void start() {
        boolean running = true;
        Scanner keyboard = new Scanner(System.in);

        while (running) {
            showMainMenu();
            int userChoice = readUserChoice(keyboard);
            switch (userChoice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    printAllClients();
                    break;
                case 2:
                    System.out.println("Picked 2");
                    break;
                case 3:
                    System.out.println("Picked 3");
                    break;
                case 4:
                    System.out.println("Picked 4");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;

            }
        }
    }
}
