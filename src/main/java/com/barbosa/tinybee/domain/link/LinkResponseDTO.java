package com.barbosa.tinybee.domain.link;

import java.time.LocalDateTime;
import java.util.UUID;

public record LinkResponseDTO(
    UUID id,
    String url,
    String shortenedUrl,
    LocalDateTime createdAt,
    boolean isActive

) {

}
