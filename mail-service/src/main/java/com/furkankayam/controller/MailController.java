package com.furkankayam.controller;

import com.furkankayam.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/verify")
    public void verifyAccount(
            @RequestParam String email,
            @RequestParam String otp) {
        mailService.accountVerify(email, otp);
    }
}