package com.example.tasks.domain.dtos;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
