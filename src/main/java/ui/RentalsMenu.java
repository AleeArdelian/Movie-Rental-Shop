package ui;

class RentalsMenu extends AbstractMenu {

    @Override
    void setUpMenu() {
        setTitle("RENTALS");
        menuItems.put(1, new MenuOption("Rent a movie", () -> System.out.println("Add")));
        menuItems.put(2, new MenuOption("Return a movie", () -> System.out.println("Update")));
        menuItems.put(0, new MenuOption("Back", () -> running = false));
    }

}
