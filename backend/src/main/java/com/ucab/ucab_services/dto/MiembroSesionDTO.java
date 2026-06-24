package com.ucab.ucab_services.dto;

import com.ucab.ucab_services.entity.RolMiembro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Subconjunto seguro de datos de Miembro para devolver al frontend
 * tras un login exitoso. Nunca incluye claveHash ni datos sensibles
 * innecesarios para la sesión.
 *
 * El campo "rol" es la base para que Angular decida qué rutas e
 * interfaces mostrar (ej. solo ADMIN_SISTEMA ve "agregar-miembro").
 * Ver RolMiembro para la convención completa de prefijos de correo.
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
    private RolMiembro rol;
}