package com.ucab.ucab_services.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Lo que Angular envía al hacer POST /api/auth/login
 */
@Getter
@Setter
public class LoginRequest {
    private String correoInstitucional;
    private String clave;
}