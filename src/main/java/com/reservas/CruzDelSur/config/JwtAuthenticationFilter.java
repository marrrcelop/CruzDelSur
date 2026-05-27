package com.reservas.CruzDelSur.config;


import com.reservas.CruzDelSur.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Importante para poder inyectarlo en SecurityConfig
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // Inyección de dependencias a través del constructor
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Obtener el header Authorization de la petición
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // 2. Comprobar si el header está ausente o no empieza con "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; // Si no hay token, dejamos que siga su camino (probablemente sea rechazado más adelante)
        }

        // 3. Extraer el token (saltando los 7 caracteres de "Bearer ")
        jwt = authHeader.substring(7);

        // 4. Extraer el usuario del token (necesitas este método en tu JwtService)
        username = jwtService.extractUsername(jwt);

        // 5. Si hay usuario y aún no está autenticado en este contexto
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Cargar los detalles del usuario desde la base de datos (o memoria)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // 6. Validar si el token es correcto y no ha expirado
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // 7. Crear el token de autenticación para Spring
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // Agregar detalles extra de la petición web (IP, sesión, etc.)
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 8. Establecer la autenticación en el contexto de Spring Security
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 9. Pasar al siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }
}
