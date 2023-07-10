package com.isc.service;

import com.isc.config.JwtService;
import com.isc.dto.AuthenticationRequest;
import com.isc.dto.AuthenticationResponse;
import com.isc.dto.RegisterRequest;
import com.isc.entity.AppUser;
import com.isc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {//can be Interface

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest register) {
        AppUser appUser = AppUser.builder()
                .username(register.getUsername())
                .password(passwordEncoder.encode(register.getPassword()))
                .firstName(register.getFirstName())
                .lastName(register.getLastName())
                .email(register.getEmail())
                .role(register.getRole())//must change !it can't get from input value
                .build();
        userRepository.save(appUser);
        String token = jwtService.generateToken(appUser);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        AppUser byUsername = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("UsernameNotFoundException:" + authenticationRequest.getUsername()));

        String token = jwtService.generateToken(byUsername);
        return AuthenticationResponse.builder().token(token).build();
    }
}
