package com.insidetrackdata.purchasereport.web.controller;

import com.insidetrackdata.purchasereport.domain.dto.AuthenticationRequest;
import com.insidetrackdata.purchasereport.domain.dto.AuthenticationResponse;
import com.insidetrackdata.purchasereport.service.ApiUserDetailsService;
import com.insidetrackdata.purchasereport.web.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication Controller.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@RestController
@CrossOrigin
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final ApiUserDetailsService apiUserDetailsService;
  private final JwtUtil jwtUtil;

  /**
   * Method that returns a JWT Token based on user credentials.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  @PostMapping("/authenticate")
  public ResponseEntity<Object> createToken(
      @RequestBody AuthenticationRequest request) {
    authenticate(request.getUsername(), request.getPassword());
    UserDetails userDetails = apiUserDetailsService.loadUserByUsername(request.getUsername());
    String jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  private void authenticate(String username, String password)
      throws DisabledException, BadCredentialsException {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new DisabledException("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("INVALID_CREDENTIALS", e);
    }
  }
}
