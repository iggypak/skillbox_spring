package org.spring_example;

import org.spring_example.components.ContactManager;
import org.spring_example.exceptions.ContactAlreadyExistsException;
import org.spring_example.exceptions.ContactValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Configuration
@ComponentScan("org.spring_example")
@PropertySource("classpath:application.properties")
public class ProfileWorker {
    @Value("${init}")
    private String initPathToContacts;

    @Value("${prod}")
    private String pathToProdContacts;

    @Profile("prod")
    @Bean("contactManager")
    public ContactManager getContactManagerProd() {
        return new ContactManager();
    }


    @Profile("init")
    @Bean("contactManager")
    public ContactManager getContactManagerInit() {
        ContactManager contactManager = new ContactManager();
        List<String> contactsValue;

        try {
            contactsValue = Files.readAllLines(Path.of(initPathToContacts));
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

    @Bean("path")
    public String pathToSave() {
        return initPathToContacts;
    }

    @Bean("path")
    public String pathToSaveProd(){
        return pathToProdContacts;
    }
}
