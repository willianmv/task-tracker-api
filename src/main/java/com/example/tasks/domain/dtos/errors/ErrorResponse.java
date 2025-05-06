package com.example.tasks.domain.dtos.errors;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
