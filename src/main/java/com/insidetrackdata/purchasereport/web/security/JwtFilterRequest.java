package com.insidetrackdata.purchasereport.web.security;

import static com.insidetrackdata.purchasereport.util.Constants.AUTHORIZATION_HEADER;

import com.insidetrackdata.purchasereport.service.ApiUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filters requests to validate JWT Token.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@Component
@AllArgsConstructor
@Slf4j
public class JwtFilterRequest extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  private final ApiUserDetailsService apiUserDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain)
      throws ServletException, IOException, IllegalArgumentException, ExpiredJwtException {
    String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
    String username = null;
    String jwt = null;
    if (StringUtils.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer")) {
      jwt = authorizationHeader.substring(7);
      username = jwtUtil.extractUsername(jwt);
    } else {
      logger.warn("JWT Token does not begin with Bearer String");
    }

    if (StringUtils.isNotEmpty(username)
        && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
      UserDetails userDetails = apiUserDetailsService.loadUserByUsername(username);
      if (jwtUtil.validateToken(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
