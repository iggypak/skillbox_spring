package org.spring_example;

import org.spring_example.exceptions.ContactAlreadyExistsException;
import org.spring_example.exceptions.ContactValidationException;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InitConfigurator implements Configurator{
    @Value("${path_to_contacts}")
    public String pathToContacts;

    @Override
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
