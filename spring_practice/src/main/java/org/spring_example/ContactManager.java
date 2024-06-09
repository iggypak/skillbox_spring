package org.spring_example;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class ContactManager {

    private final TreeMap <String, Contact> contacts = new TreeMap<>();

    public Collection<Contact> getAllContact() {
        return contacts.values();
    }

    public Contact getById(String id) {
        return contacts.get(id);
    }

    public void addContact(String id, Contact contact){
        contacts.put(id, contact);
    }

    public void deleteById(String id) {
        contacts.remove(id);
    }

    public void updateContact(String id, Contact contact){
        contacts.put(id, contact);
    }

    @Override
    public String toString() {
        String result = "";
        if (contacts.isEmpty()){
            return result;
        }
        result = getAllContact()
                .stream()
                .map(e -> e.toString())
                .reduce("", (subtotal, item)->{
                    return subtotal.concat(item).concat("\n");
                });

        return result;
    }


}
