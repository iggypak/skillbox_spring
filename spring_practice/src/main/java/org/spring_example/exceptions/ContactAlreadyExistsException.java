package org.spring_example.exceptions;

public class ContactAlreadyExistsException extends Exception{
    public ContactAlreadyExistsException(String message) {
        super(message);
    }

    public ContactAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
