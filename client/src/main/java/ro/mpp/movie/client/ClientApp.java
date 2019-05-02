package ro.mpp.movie.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.mpp.movie.client.ui.UI;

public class ClientApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("ro.mpp.movie.client.config");

        UI ui = context.getBean(UI.class);
        ui.runMainMenu();
    }

}