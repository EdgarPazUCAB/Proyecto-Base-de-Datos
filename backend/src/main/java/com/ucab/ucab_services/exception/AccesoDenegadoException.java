package com.ucab.ucab_services.exception;

/**
 * Se lanza cuando un miembro autenticado intenta acceder a un recurso
 * que requiere un rol distinto (ej. un Estudiante llamando al endpoint
 * de creación de miembros, reservado a ADMIN_SISTEMA).
 *
 * Distinta de AutenticacionException: esta excepción asume que el
 * usuario SÍ está autenticado, simplemente no tiene el rol necesario
 * (403 Forbidden, no 401 Unauthorized).
 */
public class AccesoDenegadoException extends RuntimeException {
    public AccesoDenegadoException(String mensaje) {
        super(mensaje);
    }
}