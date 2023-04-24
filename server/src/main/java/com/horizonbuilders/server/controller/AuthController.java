package com.horizonbuilders.server.controller;

import com.horizonbuilders.server.dto.LoginRequest;
import com.horizonbuilders.server.dto.LoginResponse;
import com.horizonbuilders.server.jwt.JwtUtils;
import com.horizonbuilders.server.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final AuthService authService;

    @PostMapping("/authenticate")
    public LoginResponse authenticate(@RequestBody LoginRequest loginRequest) {
        return authService.authenticate(loginRequest.username(), loginRequest.password());
    }
}
