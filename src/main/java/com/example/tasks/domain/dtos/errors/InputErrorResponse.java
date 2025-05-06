package com.example.tasks.domain.dtos.errors;

import java.util.Map;

public record InputErrorResponse(
        int status,
        String message,
        Map<String, String> errors
) {
}
