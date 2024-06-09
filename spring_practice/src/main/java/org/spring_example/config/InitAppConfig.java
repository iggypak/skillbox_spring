package org.spring_example.config;

import org.spring_example.Configurator;
import org.spring_example.InitConfigurator;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources(
        value = {
                @PropertySource("classpath:application-init.properties")
        }
)
@Profile("init")
public class InitAppConfig {

    @Bean
    public Configurator getConfigurator(){
        return new InitConfigurator();
    }

}
