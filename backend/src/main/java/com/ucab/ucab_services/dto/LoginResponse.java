package com.ucab.ucab_services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Respuesta de POST /api/auth/login.
 *
 * Dos escenarios posibles:
 *  - requiereMfa = true  -> el frontend debe mostrar la pantalla de
 *                           "ingresa el código que te enviamos", y
 *                           luego llamar a /api/auth/verificar-mfa.
 *  - requiereMfa = false -> el login ya está completo, "miembro"
 *                           trae los datos básicos de la sesión.
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private boolean exito;
    private boolean requiereMfa;
    private String mensaje;
    private MiembroSesionDTO miembro; // null si requiereMfa = true y aún no se completó
}