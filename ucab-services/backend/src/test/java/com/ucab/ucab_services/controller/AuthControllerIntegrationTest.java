package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerIntegrationTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setValidator(validator)
                .build();
    }

    @Test
    void testVerificarMfaSuccess() throws Exception {
        VerificarMfaRequest verificarMfaRequest = new VerificarMfaRequest();
        verificarMfaRequest.setCedula("12345678");
        verificarMfaRequest.setCodigo("123456");

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setToken("dummy-jwt-token");
        expectedResponse.setRoles(List.of("ESTUDIANTE"));
        expectedResponse.setNombre("Juan Perez");
        expectedResponse.setCorreo("juan.perez@ucab.edu.ve");
        expectedResponse.setCedulaMiembro("12345678");
        expectedResponse.setRequiereMfa(false);
        expectedResponse.setMensaje("MFA verified successfully");

        when(authService.verificarMfa(any(VerificarMfaRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/auth/verificar-mfa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verificarMfaRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("dummy-jwt-token"))
                .andExpect(jsonPath("$.roles[0]").value("ESTUDIANTE"))
                .andExpect(jsonPath("$.nombre").value("Juan Perez"))
                .andExpect(jsonPath("$.correo").value("juan.perez@ucab.edu.ve"))
                .andExpect(jsonPath("$.cedulaMiembro").value("12345678"))
                .andExpect(jsonPath("$.requiereMfa").value(false))
                .andExpect(jsonPath("$.mensaje").value("MFA verified successfully"));
    }

    @Test
    void testVerificarMfaValidationFailure() throws Exception {
        VerificarMfaRequest verificarMfaRequest = new VerificarMfaRequest();
        verificarMfaRequest.setCedula("");
        verificarMfaRequest.setCodigo("123");

        mockMvc.perform(post("/api/auth/verificar-mfa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verificarMfaRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.cedula").exists());
    }
}