package domain.ui;

import service.ClientRentalService;

import java.util.Scanner;

public class UI {

    private ClientRentalService crs;

    public UI(ClientRentalService crs) {
        this.crs = crs;
    }

    private static void showMainMenu() {
        System.out.println("\t\tMovie Rental Shop\n" +
                "  1. Clients\n" +
                "  2. Movies\n" +
                "  3. Rent a Movie" +
                "  4. Return a Movie" +
                "  0. Exit\n\n");
    }

    private int readUserChoice(Scanner keyboard) {
        System.out.print("Your choice: ");
        int i = keyboard.nextInt();
        if (i >= 0 && i <= 4) return i;
            else System.out.println("Invalid choice!");
        return -1;
    }

    public void start() {
        boolean running = true;
        Scanner keyboard = new Scanner(System.in);

        while (running) {
            showMainMenu();
            int userChoice = readUserChoice(keyboard);
            switch (userChoice) {
                case 0:
                    break;
            }
        }
    }
}