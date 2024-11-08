package com.martin.api.util.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskRequest(
    @NotNull(message = "El title no puede ir vacio")
    @Size(min = 5, max = 60, message = "El title debe tener entre 5 a 20 caracteres")
    String title,
    @NotNull(message = "La description no puede ir vacio")
    @Size(min = 5, max = 60, message = "El description debe tener entre 5 a 20 caracteres")
    String description
) {

}
