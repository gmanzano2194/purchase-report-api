package com.insidetrackdata.purchasereport.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO to authenticate a user.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@Getter
@Setter
public class AuthenticationRequest {
  private String username;
  private String password;
}
