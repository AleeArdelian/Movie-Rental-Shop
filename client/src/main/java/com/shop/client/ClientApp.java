package com.shop.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.shop.client.ui.UI;

public class ClientApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("com/shop/core/config");

        UI ui = context.getBean(UI.class);
        ui.runMainMenu();
    }

}