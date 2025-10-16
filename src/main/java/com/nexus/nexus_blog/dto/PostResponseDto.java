package com.nexus.nexus_blog.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostResponseDto(
        UUID id,
        String title,
        String content,
        String authorUsername, // Apenas o nome do autor, não o objeto User inteiro
        LocalDateTime creationDate
) {
}
