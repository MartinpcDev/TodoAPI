package com.martin.api.service;

import com.martin.api.persistence.model.User;

public interface IJwtService {

  String extractUsername(String token);

  String generateToken(User user);

  Boolean isTokenValid(String token, User user);
}
