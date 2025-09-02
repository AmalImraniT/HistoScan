package com.tissue.captioner.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String path = request.getRequestURI();
    String method = request.getMethod();
    
    // bypass pour preflight & endpoints publics
    if ("OPTIONS".equalsIgnoreCase(method)
        || path.startsWith("/api/auth/")
        || path.startsWith("/api/public/")
        || path.startsWith("/v3/api-docs")
        || path.startsWith("/swagger-ui")) {
      chain.doFilter(request, response);
      return;
    }
    
    // TODO: Ajouter ici la logique JWT pour les autres endpoints
    // - Lire le header Authorization: Bearer <token>
    // - Valider le token avec JwtService
    // - Créer l'authentification Spring Security
    
    chain.doFilter(request, response);
  }
}
