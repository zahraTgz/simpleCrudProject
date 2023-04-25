package com.isc.config;

import com.isc.entity.AppUser;
import com.isc.entity.AppUserRole;
import com.isc.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SetupData {

    private final UserRepository userRepository;

    public SetupData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        initUsers();
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

}
