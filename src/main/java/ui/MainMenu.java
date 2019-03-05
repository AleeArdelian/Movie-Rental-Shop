package ui;

import java.io.IOException;

public class MainMenu extends AbstractMenu {

    private AbstractMenu clientsMenu;
    private AbstractMenu moviesMenu;
    private AbstractMenu rentalsMenu;

    @Override
    void setUpMenu(){
        clientsMenu = new ClientsMenu();
        moviesMenu = new MoviesMenu();
        rentalsMenu = new RentalsMenu();
        setTitle("Main Menu");
        menuItems.put(1, new MenuOption("Clients", () -> {
            try {
                clientsMenu.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        menuItems.put(2, new MenuOption("Movies", () -> {
            try {
                moviesMenu.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        menuItems.put(3, new MenuOption("Rentals", () -> {
            try {
                rentalsMenu.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        menuItems.put(0, new MenuOption("Exit", () -> running = false));
    }
}
