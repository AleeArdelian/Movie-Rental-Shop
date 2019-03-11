package ui;

import domain.BaseEntity;
import domain.Client;
import domain.validators.UserChoiceValidator;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import service.ClientRentalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
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

    private int getUserChoice(BufferedReader keyboard) throws IOException {
        System.out.print("Your choice: ");
        String userChoice = keyboard.readLine();
        validator.setOptions(menuItems.keySet().stream().map(k -> Integer.toString(k)).collect(Collectors.toSet()));
        validator.validate(userChoice);
        return Integer.parseInt(userChoice);
    }
    
    public void run() throws IOException , ValidatorException {
        running = true;
        while (running) {
            this.show();
            int userChoice = getUserChoice(keyboard);
            menuItems.get(userChoice).performAction();
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
