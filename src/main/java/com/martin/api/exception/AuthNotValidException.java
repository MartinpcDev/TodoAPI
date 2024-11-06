package com.martin.api.exception;

public class AuthNotValidException extends RuntimeException {

  public AuthNotValidException(String message) {
    super(message);
  }
}
