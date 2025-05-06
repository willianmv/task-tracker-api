package com.example.tasks.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskListDto(
        @NotBlank(message = "Campo título é obrigatório")
        @Size(min = 3, max = 50, message = "Título deve ter entre 3 e 50 caracteres")
        String title,

        @NotBlank(message = "Campo descrição é obrigatório")
        @Size(min = 3, max = 100, message = "Título deve ter entre 3 e 100 caracteres")
        String description
) {
}
