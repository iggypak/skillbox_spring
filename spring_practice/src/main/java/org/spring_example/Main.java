package org.spring_example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Contact contact = context.getBean(Contact.class);
        contact.setMail("email");
        contact.setNumber("90909");
        contact.setFirstName("ole");
        contact.setSecondName("lukoye");
        contact.setSurname("olich");
        System.out.println(contact);
        ContactManager manager = new ContactManager();
        manager.addContact(contact.getMail(), contact);
        System.out.println(manager.toString());
    }
}