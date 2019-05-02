package ro.mpp.movie.web.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ro.mpp.movie.core.config.JPAConfig;


@Configuration
@ComponentScan({"ro.mpp.movie.core"})
@Import({JPAConfig.class})
@PropertySources({@PropertySource(value = "classpath:db.properties"),
})
public class AppLocalConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
