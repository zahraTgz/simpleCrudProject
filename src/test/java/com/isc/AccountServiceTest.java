package com.isc;

import com.isc.entity.Account;
import com.isc.repository.AccountRepository;
import com.isc.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    void getAccount() {
        List<Account> mockAccounts = new ArrayList<>();
        mockAccounts.add(Account.builder().id(1L).name("John").email("john@example.com").age(18).build());
        mockAccounts.add(Account.builder().id(2L).name("Jane").email("jane@example.com").age(60).build());
        Mockito.when(accountRepository.findAll()).thenReturn(mockAccounts);

        List<Account> result = accountService.getAccount(0,5).getContent();//todo: must check again
        Assertions.assertEquals(mockAccounts, result);
    }

    @Test
    void insert() {
        Account account = Account.builder().name("John").email("john@example.com").age(18).build();
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(account);
        Account account1 = accountService.insertAccount(account);
        Assertions.assertEquals(account.getName(), account1.getName());
    }


}
