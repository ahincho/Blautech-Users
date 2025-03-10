package com.blautech.ecommerce.users.domain.exceptions;

public class UserUnderAgeException extends Exception {
    public UserUnderAgeException(String message) {
        super(message);
    }
}
