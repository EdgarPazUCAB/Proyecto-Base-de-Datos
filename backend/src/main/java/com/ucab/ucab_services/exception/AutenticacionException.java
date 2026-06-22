package com.ucab.ucab_services.exception;

/**
 * Se lanza cuando el login falla por cualquier motivo de negocio
 * (correo no existe, clave incorrecta, cuenta bloqueada/suspendida,
 * código MFA inválido o expirado). El controlador la captura y
 * responde 401/403 según corresponda.
 */
public class AutenticacionException extends RuntimeException {
    public AutenticacionException(String mensaje) {
        super(mensaje);
    }
}