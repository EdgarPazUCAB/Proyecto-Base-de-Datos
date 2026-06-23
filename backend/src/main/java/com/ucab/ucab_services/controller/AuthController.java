package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.exception.AutenticacionException;
import com.ucab.ucab_services.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints de autenticación consumidos por la pantalla de Login
 * de Angular (frontend/src/app/pages/login).
 *
 * POST /api/auth/login           -> primer paso (correo + clave)
 * POST /api/auth/verificar-mfa   -> segundo paso (solo si login() respondió requiereMfa=true)
 *
 * @CrossOrigin: permite que Angular (localhost:4200 en desarrollo)
 * llame a este backend (localhost:8080) sin que el navegador lo
 * bloquee por política de CORS. En producción esto se reemplaza por
 * una configuración de CORS más restrictiva (dominio específico).
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (AutenticacionException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(false, false, e.getMessage(), null));
        }
    }

    @PostMapping("/verificar-mfa")
    public ResponseEntity<LoginResponse> verificarMfa(@RequestBody VerificarMfaRequest request) {
        try {
            LoginResponse response = authService.verificarMfa(request);
            return ResponseEntity.ok(response);
        } catch (AutenticacionException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(false, true, e.getMessage(), null));
        }
    }
}