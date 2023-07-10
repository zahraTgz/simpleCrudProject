package com.isc.service;

import com.isc.entity.Account;
import com.isc.exception.NotFoundException;
import com.isc.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {
    @Value("${account.not.found}")
    private String accountNotFound;

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Page<Account> getAccount(int page, int size) {
        PageRequest pr = PageRequest.of(page, size);
        return accountRepository.findAll(pr);
    }

    public Long insertAccount(Account account) {

        log.info("account with Name{} created.", account.getName());
        Account accountDb = accountRepository.save(account);
        return accountDb.getId();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Account with ID {} not found", id);
                    throw new NotFoundException(String.format(accountNotFound, id));
                });
    }

    public Account updateAccount(Long id, Account account) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Account with ID {} not found", id);
                    throw new NotFoundException(String.format(accountNotFound, id));
                });
        existingAccount.setName(account.getName());
        existingAccount.setEmail(account.getEmail());
        existingAccount.setAge(account.getAge());
        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long id) {
        log.info("account with Id {} deleted", id);
        accountRepository.deleteById(id);
    }
}
