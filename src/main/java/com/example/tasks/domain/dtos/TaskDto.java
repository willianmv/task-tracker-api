package com.example.tasks.domain.dtos;

import com.example.tasks.domain.entities.TaskPriority;
import com.example.tasks.domain.entities.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
