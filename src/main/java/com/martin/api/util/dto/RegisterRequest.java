package com.martin.api.util.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotNull(message = "El name no puede ir vacio")
    String name,
    @NotNull(message = "El email no puede ir vacio")
    @Email(message = "El email es obligatorio")
    String email,
    @NotNull(message = "El password no puede ir vacio")
    @Size(min = 5, max = 20, message = "El password debe tener entre 5 a 20 caracteres")
    String password
) {

}
