package com.martin.api.util.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
    @NotNull(message = "El email no puede ir vacio")
    @Email(message = "El email es obligatorio")
    String email,
    @NotNull(message = "El password no puede ir vacio")
    String password
) {

}
