package com.martin.api.controller;

import com.martin.api.service.IAuthService;
import com.martin.api.util.dto.AuthResponse;
import com.martin.api.util.dto.LoginRequest;
import com.martin.api.util.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final IAuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
    AuthResponse response = authService.register(request);
    System.out.println(response);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.login(request));
  }
}
