package com.isc.controller;

import com.isc.entity.Account;
import com.isc.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{page}/{size}")
    public Page<Account> getAccount(@PathVariable int page, @PathVariable int size) {
        return accountService.getAccount(page,size);
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PostMapping
    public Account insertAccount(@RequestBody Account account) {
        return accountService.insertAccount(account);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}
