package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest.getCedula(), loginRequest.getClave());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verificar-mfa")
    public ResponseEntity<LoginResponse> verificarMfa(@Valid @RequestBody VerificarMfaRequest verificarMfaRequest) {
        LoginResponse response = authService.verificarCodigoMfa(
                verificarMfaRequest.getCedula(),
                verificarMfaRequest.getCodigo()
        );
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}