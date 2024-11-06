package com.martin.api.service.impl;

import com.martin.api.exception.AuthNotValidException;
import com.martin.api.persistence.model.User;
import com.martin.api.persistence.repository.UserRepository;
import com.martin.api.service.IAuthService;
import com.martin.api.service.IJwtService;
import com.martin.api.util.dto.AuthResponse;
import com.martin.api.util.dto.LoginRequest;
import com.martin.api.util.dto.RegisterRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

  private final UserRepository userRepository;
  private final IJwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;

  @Override
  public AuthResponse register(RegisterRequest request) {
    Optional<User> userExists = userRepository.findByEmailIgnoreCase(request.email());

    if (userExists.isPresent()) {
      throw new AuthNotValidException("Ya existe un usuario registrado con este email");
    }

    User newUser = new User();
    newUser.setName(request.name());
    newUser.setEmail(request.email());
    newUser.setPassword(passwordEncoder.encode(request.password()));
    User createdUser = userRepository.save(newUser);
    String token = jwtService.generateToken(createdUser);
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.name(), request.password())
    );
    return new AuthResponse(token);
  }

  @Override
  public AuthResponse login(LoginRequest request) {
    User userExist = userRepository.findByEmailIgnoreCase(request.email())
        .orElseThrow(() -> new UsernameNotFoundException("No existe un usuario con este email"));
    if (passwordEncoder.matches(userExist.getPassword(), request.password())) {
      throw new AuthNotValidException("La constraseña es invalida");
    }
    String token = jwtService.generateToken(userExist);
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );
    return new AuthResponse(token);
  }
}
