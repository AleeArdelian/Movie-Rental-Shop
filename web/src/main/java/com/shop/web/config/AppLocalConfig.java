package com.shop.web.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import com.shop.core.config.JPAConfig;


@Configuration
@ComponentScan({"com.shop.core"})
@Import({JPAConfig.class})
@PropertySources({@PropertySource(value = "classpath:local/db.properties"),
})
public class AppLocalConfig {

    /**
     * Enables placeholders usage with SpEL expressions.
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
