package ui;

import domain.Rental;
import service.ClientRentalService;

import java.io.IOException;
import java.util.Set;

class RentalsMenu extends AbstractMenu {

    RentalsMenu(ClientRentalService crs) {
        super(crs);
    }

    private void printAllRentals(Set<Rental> rentals) {
        rentals.forEach(System.out::println);
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
        menuItems.put(3, new MenuOption("See all rentals", () -> printAllRentals(crs.getAllRentals())));
        menuItems.put(0, new MenuOption("Back", () -> running = false));
    }

}
