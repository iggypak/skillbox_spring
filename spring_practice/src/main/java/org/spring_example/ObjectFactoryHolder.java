package org.spring_example;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.ObjectFactory;

@Component
public class ObjectFactoryHolder {

    private final ObjectFactory<Contact> contactObjectFactory;

    public ObjectFactoryHolder(ObjectFactory<Contact> contactObjectFactory) {
        this.contactObjectFactory = contactObjectFactory;
    }

    public Contact getGetContact() {
        return contactObjectFactory.getObject();
    }

}
