package org.spring_example;

import org.spring_example.exceptions.ContactAlreadyExistsException;
import org.spring_example.exceptions.ContactValidationException;
import org.springframework.stereotype.Component;

@Component
public class ProfileWorker {
    private Configurator configurator;

    public ProfileWorker(Configurator configurator){
        this.configurator = configurator;
    }

    public ContactManager getContactManager() throws ContactValidationException, ContactAlreadyExistsException {
        return configurator.getContactManager();
    }
}
