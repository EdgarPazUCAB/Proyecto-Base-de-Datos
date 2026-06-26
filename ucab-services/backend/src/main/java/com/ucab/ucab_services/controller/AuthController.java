package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * CORS restringido a localhost:4200 (Angular en desarrollo). El
 * comodín "*" se quitó porque permitía que cualquier sitio web
 * llamara a este endpoint de autenticación.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = authService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error(e.getMessage()));
        }
    }

    @PostMapping("/verificar-mfa")
    public ResponseEntity<?> verificarMfa(@Valid @RequestBody VerificarMfaRequest request) {
        try {
            LoginResponse response = authService.verificarMfa(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error(e.getMessage()));
        }
    }

    private Map<String, String> error(String mensaje) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", mensaje);
        return errorDetails;
    }
}