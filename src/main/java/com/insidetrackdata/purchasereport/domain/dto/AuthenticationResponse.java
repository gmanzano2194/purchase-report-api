package com.insidetrackdata.purchasereport.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO that returns de JWT Token.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@AllArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
  private String jwt;
}
