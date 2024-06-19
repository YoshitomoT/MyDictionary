package com.example.app.exception;

public class UserRegistrationException extends Exception {

    public UserRegistrationException(String message) {
        super(message);
    }

    public static class PasswordsNotMatchingException extends UserRegistrationException {
        public PasswordsNotMatchingException(String message) {
            super(message);
        }
    }

    public static class UserAlreadyExistsException extends UserRegistrationException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }
}

