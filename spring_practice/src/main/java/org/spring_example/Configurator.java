package org.spring_example;


import org.spring_example.exceptions.ContactAlreadyExistsException;
import org.spring_example.exceptions.ContactValidationException;

public interface Configurator {

    ContactManager getContactManager();

}
