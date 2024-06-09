package org.spring_example.exceptions;

public class NotExistsContactException extends Exception{
    public NotExistsContactException(String message) {
        super(message);
    }

    public NotExistsContactException(String message, Throwable cause) {
        super(message, cause);
    }
}
