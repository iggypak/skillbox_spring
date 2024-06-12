package org.spring_example.configurators;

import org.spring_example.components.ContactManager;
import org.spring_example.exceptions.ContactAlreadyExistsException;
import org.spring_example.exceptions.ContactValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Configuration
@Profile("init")
public class InitConfigurator implements Configurator {
    @Value("${path_to_contacts.init}")
    public String pathToContacts;

    @Autowired
    private ContactManager contactManager;

    @Bean("contactManager")
    @Override
    @PostConstruct
    public ContactManager getContactManager() {
        ContactManager contactManager = new ContactManager();

        List<String> contactsValue;

        try {
            contactsValue = Files.readAllLines(Path.of(pathToContacts));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(String item: contactsValue){
            try {
                contactManager.addContact(item);
            } catch (ContactValidationException e) {
                System.err.println(e.getMessage());
            } catch (ContactAlreadyExistsException e) {
                System.err.println(e.getMessage());
            }
        }

        return contactManager;
    }
}
