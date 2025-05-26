package com.utp.jwt.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

  private final JWTService jwtService;

  private final CustomUserDetailsService userDetailsService;

  public JWTAuthenticationFilter(JWTService jwtService, CustomUserDetailsService userDetailsService) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    // Controlador de autenticación
    if (request.getServletPath().contains("/api/v1/auth")) {
      chain.doFilter(request, response);
      return;
    }

    final String authorizationHeader = request.getHeader("Authorization");
    final String token;
    final String username;

    // Sin el header authorization o el token
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }

    token = authorizationHeader.substring(7);
    username = jwtService.extractUserName(token);

    // Validando
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (!jwtService.isTokenExpired(token)) {
        Claims claims = jwtService.extractAllClaims(token);
        Long id = claims.get("id", Long.class);
        String tipo = claims.get("tipo", String.class);
        String correo = claims.get("correo", String.class);

        Map<String, Object> details = new HashMap<>();
        details.put("id", id);
        details.put("tipo", tipo);
        details.put("correo", correo);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(details);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    chain.doFilter(request, response);
  }
}
