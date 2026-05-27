package com.reservas.CruzDelSur.controller;


import com.reservas.CruzDelSur.dto.AuthRequest;
import com.reservas.CruzDelSur.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager; // Inyectamos el manager

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        try {
            // Esto validará internamente el correo (username en AuthRequest) y verificará que el password ingresado coincida con el Hash de la BD.
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            // Si pasa la línea anterior, está autenticado y generamos el token
            return jwtService.generateToken(request.getUsername());

        } catch (AuthenticationException e) {
            return "Credenciales inválidas";
        }
    }
}
