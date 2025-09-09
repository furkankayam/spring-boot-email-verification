package com.furkankayam.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("Email address " + email + " is already registered!");
    }
}