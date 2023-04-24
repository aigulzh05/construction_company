package com.horizonbuilders.server.exception;

import lombok.Builder;

import java.util.Date;

@Builder
public record ErrorResponse(
        int statusCode,
        Date timeStamp,
        String message,
        String description
) {
}
