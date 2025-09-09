package com.furkankayam.exception;

public class VerificationTimeoutException extends RuntimeException {

    public VerificationTimeoutException(String email) {
        super(String.format("Verification timeout. Please verify again using: http://localhost:8081/api/account/reVerify?email=%s", email));
    }
}