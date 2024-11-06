package com.martin.api.util.dto;

public record LoginRequest(
    String email,
    String password
) {

}
