package com.horizonbuilders.server.service;

import com.horizonbuilders.server.dto.LoginResponse;

public interface AuthService {
    LoginResponse authenticate(String username, String password);
}
