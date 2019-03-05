package ui;

class MoviesMenu extends AbstractMenu {

    @Override
    void setUpMenu() {
        setTitle("MOVIES");
        menuItems.put(1, new MenuOption("Add", () -> System.out.println("Add")));
        menuItems.put(2, new MenuOption("Update", () -> System.out.println("Update")));
        menuItems.put(3, new MenuOption("Delete", () -> System.out.println("Delete")));
        menuItems.put(4, new MenuOption("List all", () -> System.out.println("List all")));
        menuItems.put(0, new MenuOption("Back", () -> running = false));
    }

}
