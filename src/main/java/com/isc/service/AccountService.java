package com.isc.service;

import com.isc.entity.Account;
import com.isc.exception.NotFoundException;
import com.isc.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${account.not.found}")
    private String accountNotFound;

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccount() {
        return accountRepository.findAll();
    }

    public Account insertAccount(Account account) {

        logger.info("account with Name{} created.", account.getName());
        return accountRepository.save(account);
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Account with ID {} not found", id);
                    throw new NotFoundException(String.format(accountNotFound, id));
                });
    }

    public Account updateAccount(Long id, Account account) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Account with ID {} not found", id);
                    throw new NotFoundException(String.format(accountNotFound, id));
                });
        existingAccount.setName(account.getName());
        existingAccount.setEmail(account.getEmail());
        existingAccount.setAge(account.getAge());
        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long id) {
        logger.info("account with Id {} deleted", id);
        accountRepository.deleteById(id);
    }
}
