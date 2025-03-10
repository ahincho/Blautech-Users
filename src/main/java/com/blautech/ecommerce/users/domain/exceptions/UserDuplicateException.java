package com.blautech.ecommerce.users.domain.exceptions;

public class UserDuplicateException extends Exception {
    public UserDuplicateException(String message) {
        super(message);
    }
}
