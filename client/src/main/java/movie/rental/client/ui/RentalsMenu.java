package movie.rental.client.ui;

import movie.rental.common.service.RentalService;
import movie.rental.common.domain.Rental;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

class RentalsMenu extends AbstractMenu {

    RentalsMenu(RentalService crs) {
        super(crs);
    }

    private void printAllRentals(Set<Rental> rentals) {
        rentals.forEach(System.out::println);
    }

    private void printPagedRentals() {
        try {
            System.out.print("Page size: ");
            int size = Integer.valueOf(keyboard.readLine());
            crs.setPageSize(size);
            System.out.println();
            boolean printing = true;
            String choice = "n";
            while (printing) {
                if (choice.equals("n")) {
                    Set<Rental> rentals = crs.getNextRentals().get();
                    if (rentals.size() == 0) {
                        System.out.println("No more rentals");
                        break;
                    }
                    rentals.forEach(System.out::println);
                }
                System.out.println("\nPress 'n' to see the next page, 'x' to exit");
                choice = keyboard.readLine();
                if (choice.equals("x"))
                    printing = false;
            }
        } catch (IOException exc) {
            throw new RuntimeException("There was a problem with the input. Sorry!");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    private Rental getRental()
    {
        try {
            System.out.print("Rental Id: ");
            int id = Integer.parseInt(keyboard.readLine());
            System.out.print("Client Id: ");
            int cId = Integer.parseInt(keyboard.readLine());
            System.out.print("Movie Id: ");
            int mId = Integer.parseInt(keyboard.readLine());

            Rental rental = new Rental(cId,mId);
            rental.setId(id);
            return rental;
        } catch (IOException exc) {
            throw new RuntimeException("There was a problem with the input. Sorry!");
        } catch (NumberFormatException exc) {
            throw new RuntimeException("Id or Age not valid!");
        }
    }


    @Override
    void setUpMenu() {
        setTitle("RENTALS");
        menuItems.put(1, new MenuOption("Rent a movie", () -> crs.addRental(getRental())));
        menuItems.put(2, new MenuOption("Return a movie", () -> crs.deleteRental(getId())));
        menuItems.put(3, new MenuOption("List all", () -> {
            try {
                printAllRentals(crs.getAllRentals().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }));
        menuItems.put(4, new MenuOption("List paged", this::printPagedRentals));
        menuItems.put(0, new MenuOption("Back", () -> running = false));
    }

}
