package movie.rental.client.ui;

import movie.rental.common.service.RentalService;
import movie.rental.common.domain.Client;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

class ClientsMenu extends AbstractMenu {

    ClientsMenu(RentalService crs) {
        super(crs);
    }

    private Client getClient()
    {
        try {
            System.out.print("Id: ");
            int id = Integer.parseInt(keyboard.readLine());
            System.out.print("First name: ");
            String firstName = keyboard.readLine();
            System.out.print("Last name: ");
            String lastName = keyboard.readLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(keyboard.readLine());

            Client client = new Client(firstName, lastName, age);
            client.setId(id);
            return client;
        } catch (IOException exc) {
            throw new RuntimeException("There was a problem with the input. Sorry!");
        } catch (NumberFormatException exc) {
            throw new RuntimeException("Id or Age not valid!");
        }
    }

    private void printPagedClients() {
        try {
            System.out.print("Page size: ");
            int size = Integer.valueOf(keyboard.readLine());
            crs.setPageSize(size);
            System.out.println();
            boolean printing = true;
            String choice = "n";
            while (printing) {
                if (choice.equals("n")) {
                        Set<Client> clients = crs.getNextClients().get();
                        if (clients.size() == 0) {
                            System.out.println("No more clients");
                            break;
                        }
                        clients.forEach(System.out::println);
                }
                System.out.println("\nPress 'n' to see the next page, 'x' to exit");
                choice = keyboard.readLine();
                if (choice.equals("x"))
                    printing = false;
            }
        } catch (IOException | InterruptedException | ExecutionException exc) {
            throw new RuntimeException("There was a problem with the input. Sorry!");
        }
    }

    private void printAllClients(Set<Client> clients) {
        clients.forEach(System.out::println);
    }

    private void printSortedClients(List<Client> clients) {
        clients.forEach(System.out::println);
    }

    @Override
    void setUpMenu() {
        setTitle("CLIENTS");
        menuItems.put(1, new MenuOption("Add", () -> crs.addClient(getClient())));
        menuItems.put(2, new MenuOption("Update", () -> crs.updateClient(getClient())));
        menuItems.put(3, new MenuOption("Delete", () -> crs.deleteClient(getId())));
        menuItems.put(4, new MenuOption("List all", () -> crs.getAllClients().thenAccept(this::printAllClients)));
        menuItems.put(5, new MenuOption("List paged ", this::printPagedClients));
        menuItems.put(6, new MenuOption("List sorted", () -> {
            try {
                printSortedClients(crs.getAllSortedClients().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }));
        /*
        menuItems.put(7, new MenuOption("How many movies each client rented", () -> {
            System.out.println("Name\tNo. of rented movies");
            crs.moviesEachClient().forEach((k, v) -> System.out.println(k + " " + v));
        }
        ));*/
        menuItems.put(0, new MenuOption("Back", () -> running = false));
    }

}
