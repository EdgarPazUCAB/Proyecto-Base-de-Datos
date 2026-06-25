package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.dto.MiembroSesionDTO;
import com.ucab.ucab_services.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

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
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testVerificarMfaSuccess() throws Exception {
        VerificarMfaRequest verificarMfaRequest = new VerificarMfaRequest();
        verificarMfaRequest.setCedula("12345678");
        verificarMfaRequest.setCodigo("123456");

        MiembroSesionDTO sesionDTO = new MiembroSesionDTO();
        sesionDTO.setCedulaMiembro("12345678");
        sesionDTO.setNombresCompletos("Juan");

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setSuccess(true);
        expectedResponse.setMessage("MFA verified successfully");
        expectedResponse.setUsuario(sesionDTO);

        when(authService.verificarCodigoMfa("12345678", "123456")).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/auth/verificar-mfa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verificarMfaRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("MFA verified successfully"))
                .andExpect(jsonPath("$.usuario.cedulaMiembro").value("12345678"));
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