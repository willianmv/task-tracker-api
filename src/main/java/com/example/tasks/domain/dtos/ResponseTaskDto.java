package com.example.tasks.domain.dtos;

import com.example.tasks.domain.entities.TaskPriority;
import com.example.tasks.domain.entities.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseTaskDto(
        UUID id,
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority,
        TaskStatus status,
        LocalDateTime created,
        LocalDateTime updated
) {
}
