package com.shop.client.config;

import com.shop.client.ui.UI;
import com.shop.core.service.ClientRentalService;
import com.shop.core.service.ClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientAppConfig {

    @Bean
    ClientService clientService() {
        return new ClientRentalService();
    }

    @Bean
    UI ui() {
        return new UI();
    }

}
