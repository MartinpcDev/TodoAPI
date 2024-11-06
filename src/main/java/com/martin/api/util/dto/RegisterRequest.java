package com.martin.api.util.dto;

public record RegisterRequest(
    String name,
    String email,
    String password
) {

}
