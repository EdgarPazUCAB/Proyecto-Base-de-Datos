package com.ucab.ucab_services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Lo que Angular envía al hacer POST /api/miembros (pantalla
 * "agregar-miembro", accesible solo para el rol ADMIN_SISTEMA).
 *
 * No incluye clave: la clave inicial se genera/asigna por separado
 * (ver MiembroService.crear) — normalmente sería la cédula o un PIN
 * institucional definido por la UCAB, no algo que el admin escriba
 * a mano por cada persona.
 */
@Getter
@Setter
public class CrearMiembroRequest {
    private String cedulaMiembro;
    private String nombresCompletos;
    private String apellidosCompletos;
    private String sexo; // 'M', 'F', 'O'
    private LocalDate fechaNacimiento;
    private String direccionHabitacion;
    private String correoInstitucional; // debe incluir el prefijo de rol (est., prof., etc.)
    private String telefonoPersonal;
    private String claveInicial; // clave temporal asignada por administración
}