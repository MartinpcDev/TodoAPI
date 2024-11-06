package com.martin.api.service.impl;

import com.martin.api.persistence.model.User;
import com.martin.api.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements IJwtService {

  @Value("${api.secret.key}")
  private String SECRET_KEY;
  
  @Value("${api.expiration.time}")
  private Long EXPIRATION_TIME;

  @Override
  public String extractUsername(String token) {
    return getClaimsFromToken(token).getSubject();
  }

  private Claims getClaimsFromToken(String token) {
    return Jwts.parser()
        .verifyWith(generateKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  @Override
  public String generateToken(User user) {
    Date issueAt = new Date(System.currentTimeMillis());
    Date expiration = new Date(issueAt.getTime() + EXPIRATION_TIME);
    return Jwts.builder()
        .claims(Map.of("id", user.getId()))
        .subject(user.getEmail())
        .issuedAt(issueAt)
        .expiration(expiration)
        .signWith(generateKey())
        .compact();
  }

  @Override
  public Boolean isTokenValid(String token, User user) {
    final String email = extractUsername(token);
    return email.equals(user.getEmail()) && !isTokenExpired(token);
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return getClaimsFromToken(token).getExpiration();
  }

  private SecretKey generateKey() {
    final byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
