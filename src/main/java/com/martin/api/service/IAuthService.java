package com.martin.api.service;

import com.martin.api.util.dto.AuthResponse;
import com.martin.api.util.dto.LoginRequest;
import com.martin.api.util.dto.RegisterRequest;

public interface IAuthService {

  AuthResponse register(RegisterRequest request);

  AuthResponse login(LoginRequest request);
}
