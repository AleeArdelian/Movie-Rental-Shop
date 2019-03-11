package ui;

import domain.Movie;
import service.ClientRentalService;

import java.io.IOException;
import java.util.Set;

class MoviesMenu extends AbstractMenu {

    MoviesMenu(ClientRentalService crs) {
        super(crs);
    }

    private Movie getMovie() {
        try {
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
        } catch (IOException exc) {
            throw new RuntimeException("There was a problem with the input. Sorry!");
        } catch (NumberFormatException exc) {
            throw new RuntimeException("Id or Age not valid!");
        }
    }

    private void printAllMovies(Set<Movie> movies) {
        movies.forEach(System.out::println);
    }

    @Override
    void setUpMenu() {
        setTitle("MOVIES");
        menuItems.put(1, new MenuOption("Add", () -> crs.addMovie(getMovie())));
        menuItems.put(2, new MenuOption("Update", () -> crs.updateMovie(getMovie())));
        menuItems.put(3, new MenuOption("Delete", () -> crs.deleteMovie(getId())));
        menuItems.put(4, new MenuOption("List all", () -> printAllMovies(crs.getAllMovies())));
        menuItems.put(0, new MenuOption("Back", () -> running = false));
    }

}
