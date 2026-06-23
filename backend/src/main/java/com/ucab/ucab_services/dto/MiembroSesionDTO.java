package com.ucab.ucab_services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Subconjunto seguro de datos de Miembro para devolver al frontend
 * tras un login exitoso. Nunca incluye claveHash ni datos sensibles
 * innecesarios para la sesión.
 */
@Getter
@Setter
@AllArgsConstructor
public class MiembroSesionDTO {
    private String cedulaMiembro;
    private String nombresCompletos;
    private String apellidosCompletos;
    private String correoInstitucional;
    private String estadoCuenta;
    private String tipoCategoria; // puede ser null
}