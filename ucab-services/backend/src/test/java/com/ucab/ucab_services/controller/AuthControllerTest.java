package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.dto.MiembroSesionDTO;
import com.ucab.ucab_services.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
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
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testLoginSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCedula("12345678");
        loginRequest.setClave("password123");

        MiembroSesionDTO sesionDTO = new MiembroSesionDTO();
        sesionDTO.setCedulaMiembro("12345678");
        sesionDTO.setNombresCompletos("Juan");
        sesionDTO.setApellidosCompletos("Perez");
        sesionDTO.setCorreoInstitucional("juan.perez@ucab.edu.ve");
        sesionDTO.setEstadoCuenta("Activo");
        sesionDTO.setRol("ESTUDIANTE");

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setSuccess(true);
        expectedResponse.setMessage("Login successful");
        expectedResponse.setUsuario(sesionDTO);

        // ✅ Se cambia a any(LoginRequest.class)
        when(authService.login(any(LoginRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.usuario.cedulaMiembro").value("12345678"))
                .andExpect(jsonPath("$.usuario.nombresCompletos").value("Juan"));
    }

    @Test
    void testLoginValidationFailure() throws Exception {
        LoginRequest loginRequest = new LoginRequest();

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.cedula").exists())
                .andExpect(jsonPath("$.clave").exists());
    }
}