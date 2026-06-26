package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setValidator(validator)
                .build();
    }

    @Test
    void testLoginSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCorreo("juan.perez@ucab.edu.ve");
        loginRequest.setClave("password123");

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setToken("dummy-jwt-token");
        expectedResponse.setRoles(List.of("ESTUDIANTE"));
        expectedResponse.setNombre("Juan Perez");
        expectedResponse.setCorreo("juan.perez@ucab.edu.ve");
        expectedResponse.setCedulaMiembro("12345678");
        expectedResponse.setRequiereMfa(false);
        expectedResponse.setMensaje("Login successful");

        when(authService.login(any(LoginRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("dummy-jwt-token"))
                .andExpect(jsonPath("$.roles[0]").value("ESTUDIANTE"))
                .andExpect(jsonPath("$.nombre").value("Juan Perez"))
                .andExpect(jsonPath("$.correo").value("juan.perez@ucab.edu.ve"))
                .andExpect(jsonPath("$.cedulaMiembro").value("12345678"))
                .andExpect(jsonPath("$.requiereMfa").value(false))
                .andExpect(jsonPath("$.mensaje").value("Login successful"));
    }

    @Test
    void testLoginValidationFailure() throws Exception {
        LoginRequest loginRequest = new LoginRequest();

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.correo").exists())
                .andExpect(jsonPath("$.clave").exists());
    }
}