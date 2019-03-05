package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AbstractMenu {

    SortedMap<Integer, MenuOption> menuItems;
    private BufferedReader keyboard;
    private String title;
    boolean running = true;

    AbstractMenu() {
        menuItems = new TreeMap<>();
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        setUpMenu();
    }

    abstract void setUpMenu();

    void setTitle(String title) {
        this.title = title;
    }

    private void show() {
        System.out.print("\t\t" + title + "\n\n");
        menuItems.forEach((key, value) -> System.out.println("  " + key + ". " + value.getText()));
        System.out.println();
    }

    private int getUserChoice(BufferedReader keyboard) throws IOException {
        System.out.print("Your choice: ");
        String userChoice = keyboard.readLine();
        System.out.println();
        return Integer.parseInt(userChoice);
    }

    public void run() throws IOException{
        running = true;
        while (running) {
            this.show();
            try {
                int userChoice = getUserChoice(keyboard);
                menuItems.get(userChoice).performAction();
            }
            catch (IOException exc) {
                exc.printStackTrace();
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
