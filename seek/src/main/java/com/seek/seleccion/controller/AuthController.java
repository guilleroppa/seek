package com.seek.seleccion.controller;

import com.seek.seleccion.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if ("patty".equals(username) && "1234".equals(password)) {
            LocalTime ahora = LocalTime.now();
            if (ahora.isAfter(LocalTime.of(21,0))) {
                return ResponseEntity.unprocessableEntity()
                        .body("{\"status\":422,\"error\":\"Unprocessable Entity\",\"message\":\"Login no permitido fuera de horario\"}");
            }
            String token = jwtUtil.generarToken(username, "ADMIN"); // 👈 rol dinámico
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

}