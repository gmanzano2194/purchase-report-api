package com.insidetrackdata.purchasereport.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response object for errors.
 *
 * @author Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
  private int code;
  private String message;
  private Map<String, String> detail;
}