package ui;

import domain.Client;
import service.ClientRentalService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

class ClientsMenu extends AbstractMenu {

    ClientsMenu(ClientRentalService crs) {
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
        menuItems.put(4, new MenuOption("List all", () -> printAllClients(crs.getAllClients())));
        menuItems.put(5, new MenuOption("List sorted", () -> printSortedClients(crs.getAllSortedClients())));
        menuItems.put(6, new MenuOption("How many movies each client rented", () -> {
            System.out.println("Name\tNo. of rented movies");
            crs.moviesEachClient().forEach((k, v) -> System.out.println(k + " " + v));
        }
        ));
        menuItems.put(0, new MenuOption("Back", () -> running = false));
    }

}
