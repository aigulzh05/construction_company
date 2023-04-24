package com.horizonbuilders.server.controller;

import com.horizonbuilders.server.dto.request.LoginRequest;
import com.horizonbuilders.server.dto.request.RefreshAccessTokenRequest;
import com.horizonbuilders.server.dto.response.LoginResponse;
import com.horizonbuilders.server.service.AuthService;
import com.horizonbuilders.server.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final AuthService authService;
    final RefreshTokenService refreshTokenService;

    @PostMapping("/authenticate")
    public LoginResponse authenticate(@RequestBody LoginRequest loginRequest) {
        return authService.authenticate(loginRequest.username(), loginRequest.password());
    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshAccessTokenByRefreshToken(@Valid @RequestBody RefreshAccessTokenRequest request) {
        return refreshTokenService.generateAccessTokenByRefreshToken(request);
    }
}
