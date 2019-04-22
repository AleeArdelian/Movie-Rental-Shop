import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.UI;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(
                        "com/shop/core/config"
                );

        UI ui = context.getBean(UI.class);
        ui.runMainMenu();

    }


}