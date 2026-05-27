package com.reservas.CruzDelSur.controller;


import com.reservas.CruzDelSur.dto.AuthRequest;
import com.reservas.CruzDelSur.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        if(request.getUsername().equals("admin")
                && request.getPassword().equals("1234")) {
            return jwtService.generateToken(request.getUsername());
        }
        return "Credenciales inválidas";
    }
}
