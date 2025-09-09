package com.furkankayam.util;

import java.util.Random;

public class MailUtil {

    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static String generateContent(String email, String otp) {
        return "http://localhost:8082/api/mail/verify?email=" + email + "&otp=" + otp;
    }
}