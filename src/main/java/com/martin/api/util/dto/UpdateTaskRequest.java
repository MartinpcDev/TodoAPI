package com.martin.api.util.dto;

import jakarta.validation.constraints.Size;

public record UpdateTaskRequest(
    @Size(min = 5, max = 60, message = "El title debe tener entre 5 a 20 caracteres")
    String title,
    @Size(min = 5, max = 60, message = "El description debe tener entre 5 a 20 caracteres")
    String description
) {

}
