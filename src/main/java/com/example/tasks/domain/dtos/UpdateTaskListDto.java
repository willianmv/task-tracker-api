package com.example.tasks.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateTaskListDto(
        @NotNull(message = "Campo ID da tarefa é obrigatório")
        UUID id,

        @NotBlank(message = "Campo título é obrigatório")
        @Size(min = 3, max = 50, message = "Título deve ter entre 3 e 50 caracteres")
        String title,

        @NotBlank(message = "Campo descrição é obrigatório")
        @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
        String description
) {
}
