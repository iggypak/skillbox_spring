package org.spring_example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources(
        value = @PropertySource("classpath:application-prod.properties")
)
@Profile("prod")
public class ProdAppConfig {
}
