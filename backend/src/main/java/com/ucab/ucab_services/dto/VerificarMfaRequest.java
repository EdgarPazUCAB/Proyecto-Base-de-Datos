package com.ucab.ucab_services.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Lo que Angular envía al hacer POST /api/auth/verificar-mfa,
 * después de que el usuario escribe el código que recibió.
 */
@Getter
@Setter
public class VerificarMfaRequest {
    private String cedulaMiembro;
    private String codigo;
}