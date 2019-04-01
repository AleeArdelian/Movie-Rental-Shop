package movie.rental.client.ui;

import movie.rental.common.HelloService;
import movie.rental.common.domain.Movie;

import java.io.IOException;
import java.util.List;
import java.util.Set;

class MoviesMenu extends AbstractMenu {

    MoviesMenu(HelloService crs) {
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

    private int getYear() {
        try {
            System.out.print("Year: ");
            return Integer.parseInt(keyboard.readLine());
        } catch (IOException exc) {
            throw new RuntimeException("There was a problem with the input. Sorry!");
        } catch (NumberFormatException exc) {
            throw new RuntimeException("Year not valid");
        }
    }

    private void printAllMovies(Set<Movie> movies) {
        movies.forEach(System.out::println);
    }

    private void printSortedMovies(List<Movie> movies) {
        movies.forEach(System.out::println);
    }

    private void printPagedMovies() {
        try {
            System.out.print("Page size: ");
            int size = Integer.valueOf(keyboard.readLine());
            crs.setPageSize(size);
            System.out.println();
            boolean printing = true;
            String choice = "n";
            while (printing) {
                if (choice.equals("n")) {
                    Set<Movie> movies = crs.getNextMovies();
                    if (movies.size() == 0) {
                        System.out.println("No more movies");
                        break;
                    }
                    movies.forEach(System.out::println);
                }
                System.out.println("\nPress 'n' to see the next page, 'x' to exit");
                choice = keyboard.readLine();
                if (choice.equals("x"))
                    printing = false;
            }
        } catch (IOException exc) {
            throw new RuntimeException("There was a problem with the input. Sorry!");
        }
    }

    @Override
    void setUpMenu() {
        setTitle("MOVIES");
        menuItems.put(1, new MenuOption("Add", () -> crs.addMovie(getMovie())));
        menuItems.put(2, new MenuOption("Update", () -> crs.updateMovie(getMovie())));
        menuItems.put(3, new MenuOption("Delete", () -> crs.deleteMovie(getId())));
        menuItems.put(4, new MenuOption("List all", () -> printAllMovies(crs.getAllMovies())));
        menuItems.put(5, new MenuOption("List paged", this::printPagedMovies));
        menuItems.put(6, new MenuOption("List sorted", () -> printSortedMovies(crs.getAllSortedMovies())));
        menuItems.put(7, new MenuOption("List all movies after a given year", () -> System.out.println("The number" +
                        " of movies after given year is: " + crs.getNoMoviesAfterYear(getYear()))));
        menuItems.put(8, new MenuOption("How many times movies were rented", () -> System.out.println("The number" +
                " of movies after given year is: " + crs.getNoMoviesAfterYear(getYear()))));
        menuItems.put(0, new MenuOption("Back", () -> running = false));
    }

}
