package com.horizonbuilders.server.dto;

public record LoginRequest(
        String username,
        String password
) {
}
