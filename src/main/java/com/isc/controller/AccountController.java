package com.isc.controller;

import com.isc.dto.AccountSearchCriteriaDto;
import com.isc.entity.Account;
import com.isc.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<Account>> getAccount(@PathVariable int page, @PathVariable int size) {
        return ResponseEntity.ok(accountService.getAccount(page, size));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> insertAccount(@Valid @RequestBody Account account) {
        return ResponseEntity.ok(accountService.insertAccount(account));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id,@Valid @RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateAccount(id, account));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("/searchOnAccount") //sample in url :page=0&size=10&sort=name,asc
    public ResponseEntity<Page<Account>> searchAccount(@RequestBody AccountSearchCriteriaDto searchCriteriaDto, Pageable pageable) {
        return ResponseEntity.ok(accountService.searchAccount(searchCriteriaDto, pageable));
    }

}
