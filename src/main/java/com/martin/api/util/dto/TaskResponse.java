package com.martin.api.util.dto;

import lombok.Builder;

@Builder
public record TaskResponse(
    Long id,
    String title,
    String description
) {

}
