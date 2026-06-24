package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.dto.MiembroSesionDTO;
import com.ucab.ucab_services.service.AuthService;
import com.ucab.ucab_services.controller.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Add our global exception handler
                .build();
    }

    @Test
    void testLoginSuccess() throws Exception {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCedula("12345678");
        loginRequest.setClave("password123");

        LoginResponse expectedResponse = new LoginResponse(
                true,
                "Login successful",
                new com.ucab.ucab_services.dto.MiembroSesionDTO(
                        "12345678",
                        "Juan Perez",
                        "juan.perez@ucab.edu.ve",
                        "Activo"
                )
        );

        when(authService.login("12345678", "password123")).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.data.cedula").value("12345678"))
                .andExpect(jsonPath("$.data.nombreCompleto").value("Juan Perez"));
    }

    @Test
    void testLoginValidationFailure() throws Exception {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        // Intentionally leaving fields empty to trigger validation

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.cedula").exists())
                .andExpect(jsonPath("$.clave").exists());
    }

    @Test
    void testVerificarMfaSuccess() throws Exception {
        // Arrange
        VerificarMfaRequest verificarMfaRequest = new VerificarMfaRequest();
        verificarMfaRequest.setCedula("12345678");
        verificarMfaRequest.setCodigo("123456");

        LoginResponse expectedResponse = new LoginResponse(
                true,
                "MFA verified successfully",
                new com.ucab.ucab_services.dto.MiembroSesionDTO(
                        "12345678",
                        "Juan Perez",
                        "juan.perez@ucab.edu.ve",
                        "Activo"
                )
        );

        when(authService.verificarCodigoMfa("12345678", "123456")).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(post("/api/auth/verificar-mfa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verificarMfaRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("MFA verified successfully"))
                .andExpect(jsonPath("$.data.cedula").value("12345678"));
    }

    @Test
    void testVerificarMfaValidationFailure() throws Exception {
        // Arrange
        VerificarMfaRequest verificarMfaRequest = new VerificarMfaRequest();
        // Intentionally leaving fields empty or invalid to trigger validation
        verificarMfaRequest.setCedula(""); // Empty cedula
        verificarMfaRequest.setCodigo("123"); // Invalid code (not 6 digits)

        // Act & Assert
        mockMvc.perform(post("/api/auth/verificar-mfa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verificarMfaRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.cedula").exists());
    }
}