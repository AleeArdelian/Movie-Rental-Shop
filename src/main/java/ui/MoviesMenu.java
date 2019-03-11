package ui;

import domain.Movie;
import service.ClientRentalService;

import java.io.IOException;
import java.util.Set;

class MoviesMenu extends AbstractMenu {

    MoviesMenu(ClientRentalService crs) {
        super(crs);
    }

    private Movie getMovie() throws IOException {
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

    private void printAllMovies(Set<Movie> movies) {
        movies.forEach(System.out::println);
    }

    @Override
    void setUpMenu() {
        setTitle("MOVIES");
        menuItems.put(1, new MenuOption("Add", () -> {
            try {
                crs.addMovie(getMovie());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        menuItems.put(2, new MenuOption("Update", () -> {
            try {
                crs.updateMovie(getMovie());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        menuItems.put(3, new MenuOption("Delete", () -> System.out.println("Delete")));
        menuItems.put(4, new MenuOption("List all", () -> printAllMovies(crs.getAllMovies())));
        menuItems.put(0, new MenuOption("Back", () -> running = false));
    }

}
