package com.furkankayam.exception;

public class InvalidOtpException extends RuntimeException {

    public InvalidOtpException(String otp) {
        super(String.format("Invalid OTP: %s", otp));
    }
}