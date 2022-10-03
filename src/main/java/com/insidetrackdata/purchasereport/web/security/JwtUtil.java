package com.insidetrackdata.purchasereport.web.security;

import static com.insidetrackdata.purchasereport.util.Constants.JWT_EXPIRATION_TIME;
import static com.insidetrackdata.purchasereport.util.Utils.getKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.HashMap;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Class that controls the generation of JWT Tokens.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@Component
public class JwtUtil {

  /**
   * Generates a new JWT Token based on UserDetails.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-25
   */
  public String generateToken(UserDetails userDetails) {
    return Jwts.builder().setClaims(new HashMap<>())
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
        .signWith(getKey())
        .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    return (userDetails.getUsername().equals(extractUsername(token))) && !isTokenExpired(token);
  }

  public String extractUsername(String token) {
    return getClaims(token).getSubject();
  }

  public boolean isTokenExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }

  private Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token).getBody();
  }
}
