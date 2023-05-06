package com.isc.dto;

import com.isc.entity.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private AppUserRole role;
}
