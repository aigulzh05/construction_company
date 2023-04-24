package com.horizonbuilders.server.service.impl;

import com.horizonbuilders.server.exception.UserNotFoundException;
import com.horizonbuilders.server.model.RefreshToken;
import com.horizonbuilders.server.model.User;
import com.horizonbuilders.server.repository.RefreshTokenRepository;
import com.horizonbuilders.server.repository.UserRepository;
import com.horizonbuilders.server.service.RefreshTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenServiceImpl implements RefreshTokenService {
    final UserRepository userRepository;
    final RefreshTokenRepository refreshTokenRepository;
    @Value("${jwt_refresh_token_expiration_in_hours}")
    long jwt_refresh_token_expiration_in_hours;

    @Override
    public String generateRefreshToken(User user) {

        RefreshToken refreshToken = null;
        if (refreshTokenRepository.existsByUser(user)) {
            refreshToken = refreshTokenRepository.findByUser(user)
                    .orElseThrow(UserNotFoundException::new);
            refreshTokenRepository.delete(refreshToken);
        }
        refreshToken = generateCompleteNewRefreshToken(user);
        return refreshToken.getRefreshToken();
    }

    private RefreshToken generateCompleteNewRefreshToken(User user) {
        return refreshTokenRepository.save(
                RefreshToken.builder()
                        .refreshToken(UUID.randomUUID().toString())
                        .user(user)
                        .expirityDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * jwt_refresh_token_expiration_in_hours))
                        .build()
        );
    }
}
