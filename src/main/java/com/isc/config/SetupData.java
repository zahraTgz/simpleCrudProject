package com.isc.config;

import com.isc.entity.Account;
import com.isc.entity.AppUser;
import com.isc.entity.AppUserRole;
import com.isc.repository.AccountRepository;
import com.isc.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SetupData {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    public SetupData(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void init() {
        initUsers();
        initAccount();
    }

    private void initUsers() {

        AppUser adminUser = AppUser.builder()
                .username("admin")
                .password("admin")
                .role(AppUserRole.ADMIN)
                .build();
        userRepository.save(adminUser);

        AppUser user = AppUser.builder()
                .username("user")
                .password("user")
                .role(AppUserRole.USER)
                .build();
        userRepository.save(user);
    }

    private void initAccount() {
        Account firstAccount = Account.builder().name("zahra").email("z_tgz@isc.com").age(22).build();
        accountRepository.save(firstAccount);
        Account secondAccount = Account.builder().name("elahe").email("e_tgz@isc.com").age(25).build();
        accountRepository.save(secondAccount);
    }

}
