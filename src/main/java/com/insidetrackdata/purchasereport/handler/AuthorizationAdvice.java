package com.insidetrackdata.purchasereport.handler;

import com.insidetrackdata.purchasereport.domain.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class that handles Unauthorized requests.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@ControllerAdvice
@Slf4j
public class AuthorizationAdvice {

  @ResponseBody
  @ExceptionHandler({ExpiredJwtException.class, SignatureException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  ResponseEntity<Object> unauthorizedHandler(Exception ex) {
    log.error("hidden error => " + ex.getMessage());
    ErrorResponse errorResponse = ErrorResponse.builder()
        .code(HttpStatus.UNAUTHORIZED.value())
        .message("Access token is invalid.")
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

}