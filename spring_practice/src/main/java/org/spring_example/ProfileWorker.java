package org.spring_example;

import org.spring_example.components.ContactManager;
import org.spring_example.configurators.InitConfigurator;
import org.spring_example.configurators.ProdConfigurator;
import org.spring_example.exceptions.ContactAlreadyExistsException;
import org.spring_example.exceptions.ContactValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("org.spring_example")
public class ProfileWorker {

    @Bean("contactManager")
    @Profile("prod")
    public ContactManager getContactManager() throws ContactValidationException, ContactAlreadyExistsException {
        return new ProdConfigurator().getContactManager();
    }

    @Bean("contactManager")
    @Profile("init")
    public ContactManager getContactManagerInit() throws ContactValidationException, ContactAlreadyExistsException {
        return new InitConfigurator().getContactManager();
    }
}
