package com.horizonbuilders.server.service;

import com.horizonbuilders.server.model.User;

public interface RefreshTokenService {
    String generateRefreshToken(User user);
}
