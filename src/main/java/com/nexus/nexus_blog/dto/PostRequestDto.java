package com.nexus.nexus_blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequestDto(
        @NotBlank(message = "O título é obrigatório.")
        @Size(min = 5, message = "O título deve ter no mínimo 5 caracteres.")
        String title,
        @NotBlank(message = "O conteúdo é obrigatório.")
        String content
) {
}
