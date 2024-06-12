package org.spring_example.components;

import org.spring_example.exceptions.ContactAlreadyExistsException;
import org.spring_example.exceptions.ContactValidationException;
import org.spring_example.exceptions.NotExistsContactException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.TreeMap;

@Component
public class ContactManager {

    private final static String DELIMITER = "(\\||;)";
    private final static String COMMON_EXCEPTION = "Некорректный ввод контакта";
    private final static String INVALID_NAME_MSG = "Некорректный ввод ФИО";
    private final static String INVALID_EMAIL_MSG = "Некорректный ввод почты";
    private final static String INVALID_PHONE_MSG = "Некорректный ввод номера телефона";
    private final static String PHONE_REGEX = "^(\\+7|8){0,1}([0-9]{10})";
    private final static String NAME_REGEX = "[a-zA-ZА-Яа-я ]+";
    private final static String EMAIL_REGEX = "([a-z]+[0-9._-]*)+@[a-z]+.[a-z]+";
    private static final String ALREADY_EXISTS_MSG = "Контакт уже существует";
    private final static String NOT_EXISTS_MSG = "контакта не существует";

    private final TreeMap <String, Contact> contacts = new TreeMap<>();

    public Collection<Contact> getAllContact() {
        return contacts.values();
    }

    public static Contact parseContact(String rawContact, String delimiter) throws ContactValidationException {
        if (rawContact.isEmpty() && rawContact.isBlank()){
            throw new ContactValidationException(COMMON_EXCEPTION);
        }
        String[] items = rawContact.split(delimiter);
        if(items.length != 3) {
            throw new ContactValidationException(COMMON_EXCEPTION);
        }

        String name = items[0];
        String email = items[2];
        String phone = items[1];

        if(!isValidName(name)){
            throw new ContactValidationException(INVALID_NAME_MSG);
        }
        if (!isValidEmail(email)){
            throw new ContactValidationException(INVALID_EMAIL_MSG);
        }
        if (!isValidPhoneNumber(phone)){
            throw new ContactValidationException(INVALID_PHONE_MSG);
        }
        Contact contact = new Contact();
        contact.setName(name);
        contact.setMail(email);
        contact.setNumber(phone);
        return contact;
    }

    public Contact getById(String id) throws NotExistsContactException {
        if(contacts.containsKey(id)){
            throw new NotExistsContactException(NOT_EXISTS_MSG);
        }
        return contacts.get(id);
    }

    public void addContact(String contact) throws ContactValidationException, ContactAlreadyExistsException {
        Contact currentContact = parseContact(contact, DELIMITER);
        if (contacts.containsKey(currentContact.getMail())){
            throw new ContactAlreadyExistsException(ALREADY_EXISTS_MSG);
        }
        contacts.put(currentContact.getMail(), currentContact);
    }

    public void deleteById(String id) throws NotExistsContactException {
        if(contacts.containsKey(id)){
            throw new NotExistsContactException(NOT_EXISTS_MSG);
        }
        contacts.remove(id);
    }

    public void updateContact(String contact) throws ContactValidationException, NotExistsContactException {
        Contact currentContact = parseContact(contact, DELIMITER);
        if(!contacts.containsKey(currentContact.getMail())){
            throw new NotExistsContactException(NOT_EXISTS_MSG);
        }
        contacts.put(currentContact.getMail(), currentContact);
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

    private static boolean isValidEmail(String email){
        if(email.matches(EMAIL_REGEX)){
            return true;
        }
        return false;
    }

    private static boolean isValidPhoneNumber(String number){
        if (number.matches(PHONE_REGEX)){
            return true;
        }
        return false;
    }

    private static boolean isValidName(String name){
        if (name.matches(NAME_REGEX)){
            return true;
        }
        return false;
    }




}
