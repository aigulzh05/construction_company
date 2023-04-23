package com.horizonbuilders.server.controller;

import com.horizonbuilders.server.dto.LoginRequest;
import com.horizonbuilders.server.jwt.JwtUtils;
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
    final AuthenticationManager authenticationManager;
    final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password()
        );
        authenticationManager.authenticate(token);
        String jwtToken = jwtUtils.generate(loginRequest.username());
        return ResponseEntity.ok(jwtToken);
    }
}
