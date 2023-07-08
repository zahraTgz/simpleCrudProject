package com.isc.controller;

import com.isc.entity.Account;
import com.isc.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{page}/{size}")
    @Secured("ROLE_USER")
    @PreAuthorize("hasRole('USER')")
    public Page<Account> getAccount(@PathVariable int page, @PathVariable int size) {
        return accountService.getAccount(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void insertAccount(@RequestBody Account account) {
        accountService.insertAccount(account);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}
