package ro.mpp.movie.client.config;

import org.springframework.web.client.RestTemplate;
import ro.mpp.movie.client.ui.UI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientAppConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    UI ui() {
        return new UI();
    }

}
