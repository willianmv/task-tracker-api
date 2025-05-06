package com.example.tasks.domain.dtos;

import com.example.tasks.domain.entities.TaskPriority;
import com.example.tasks.domain.entities.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateTaskDto(
        @NotNull(message = "Campo ID da tarefa é obrigatório")
        UUID id,

        @NotBlank(message = "Campo título é obrigatório")
        @Size(min = 3, max = 50, message = "Título deve ter entre 3 e 50 caracteres")
        String title,

        @NotBlank(message = "Campo descrição é obrigatório")
        @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
        String description,

        @NotNull(message = "Campo data de vencimento é obrigatória")
        @FutureOrPresent(message = "Data de vencimento não pode ser no passado")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dueDate,

        @NotNull(message = "Campo prioridade é obrigatório (LOW, MEDIUM ou HIGH)")
        TaskPriority priority,

        @NotNull(message = "Campo prioridade é obrigatório (OPEN ou CLOSED)")
        TaskStatus status
) {
}
