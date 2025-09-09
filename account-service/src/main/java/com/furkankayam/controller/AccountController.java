package com.furkankayam.controller;

import com.furkankayam.dto.AccountRequestDto;
import com.furkankayam.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/save")
    public void saveAccount(@RequestBody AccountRequestDto account) {
        accountService.saveAccount(account);
    }

    @GetMapping("/reVerify")
    public void reVerifyAccount(@RequestParam String email) {
        accountService.reVerifyAccount(email);
    }
}