package org.spring_example.exceptions;

public class ContactValidationException extends Exception{
    public ContactValidationException(String message) {
        super(message);
    }

    public ContactValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
