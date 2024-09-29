package com.isc.dto;

import com.isc.entity.AppUserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;

    private String firstName;

    private String lastName;

    private AppUserRole role;
}
