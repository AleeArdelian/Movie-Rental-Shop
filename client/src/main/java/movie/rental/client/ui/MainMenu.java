package movie.rental.client.ui;

import movie.rental.common.RentalService;

public class MainMenu extends AbstractMenu {

    private AbstractMenu clientsMenu;
    private AbstractMenu moviesMenu;
    private AbstractMenu rentalsMenu;

    public MainMenu(RentalService crs) {
        super(crs);
    }

    @Override
    void setUpMenu(){
        clientsMenu = new ClientsMenu(crs);
        moviesMenu = new MoviesMenu(crs);
        rentalsMenu = new RentalsMenu(crs);
        setTitle("Main Menu");
        menuItems.put(1, new MenuOption("Clients", () -> clientsMenu.run()));
        menuItems.put(2, new MenuOption("Movies", () -> moviesMenu.run()));
        menuItems.put(3, new MenuOption("Rentals", () -> rentalsMenu.run()));
        menuItems.put(0, new MenuOption("Exit", () -> running = false));
    }

}
