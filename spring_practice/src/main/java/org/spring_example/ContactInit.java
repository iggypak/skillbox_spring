package org.spring_example;

import org.spring_example.components.ContactManager;
import org.spring_example.exceptions.ContactAlreadyExistsException;
import org.spring_example.exceptions.ContactValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Configuration
@PropertySources(
        value = {
                @PropertySource("classpath:application-init.properties")
        }
)
@Profile("init")
public class ContactInit {

    @Value("${init}")
    private String initPathToContacts;


    private ContactManager contactManager;

    public ContactInit(ContactManager contactManager){
        this.contactManager = contactManager;
    }


    @PostConstruct
    public void initContactManager(){
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
    }



}
