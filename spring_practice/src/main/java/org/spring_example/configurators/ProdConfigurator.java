package org.spring_example.configurators;

import org.spring_example.components.ContactManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfigurator implements Configurator {

    @Autowired
    ContactManager cm;

    @Bean("contactManager")
    @Override
    public ContactManager getContactManager() {
        return cm;
    }
}
