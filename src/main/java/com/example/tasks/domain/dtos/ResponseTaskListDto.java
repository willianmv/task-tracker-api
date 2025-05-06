package com.example.tasks.domain.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ResponseTaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        LocalDateTime created,
        LocalDateTime updated,
        List<ResponseTaskDto> tasks
) {
}
