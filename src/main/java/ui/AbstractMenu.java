package ui;

import domain.validators.UserChoiceValidator;
import service.ClientRentalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class AbstractMenu {

    private String title;
    private UserChoiceValidator validator;

    boolean running = true;
    SortedMap<Integer, MenuOption> menuItems;
    ClientRentalService crs;
    BufferedReader keyboard;

    AbstractMenu(ClientRentalService crs) {
        menuItems = new TreeMap<>();
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        validator = new UserChoiceValidator();
        this.crs = crs;
        setUpMenu();
    }

    abstract void setUpMenu();

    void setTitle(String title) {
        this.title = title;
    }

    private void show() {
        System.out.print("\n\t\t" + title + "\n\n");
        menuItems.forEach((key, value) -> System.out.println("  " + key + ". " + value.getText()));
        System.out.println();
    }

    private int getUserChoice() {
        System.out.print("Your choice: ");
        try {
            String userChoice = keyboard.readLine();
            validator.setOptions(menuItems.keySet().stream().map(k -> Integer.toString(k)).collect(Collectors.toSet()));
            validator.validate(userChoice);
            return Integer.parseInt(userChoice);
        } catch (IOException exc) {
            throw new RuntimeException("There was a problem with the input. Sorry!");
        } catch (NumberFormatException exc) {
            throw new RuntimeException("Invalid id!");
        }
    }

    protected int getId() {
        try {
            System.out.print("Id: ");
            return Integer.parseInt(keyboard.readLine());
        } catch (NumberFormatException exc) {
            throw new RuntimeException("Invalid id!");
        } catch (IOException exc) {
            throw new RuntimeException("There was a problem with the input. Sorry!");
        }
    }
    
    public void run() {
        running = true;
        while (running) {
            this.show();
            try {
                int userChoice = getUserChoice();
                menuItems.get(userChoice).performAction();
            } catch (RuntimeException exc) {
                System.out.println(exc.getMessage());
            }
        }
    }

}

class MenuOption {

    private String text;
    private Runnable action;

    MenuOption(String text, Runnable action) {
        this.text = text;
        this.action = action;
    }

    String getText() {
        return text;
    }

    void performAction() {
        action.run();
    }

}
