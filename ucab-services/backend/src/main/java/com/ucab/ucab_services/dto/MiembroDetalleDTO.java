package com.ucab.ucab_services.dto;

import com.ucab.ucab_services.entity.RolMiembro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Vista detallada de un Miembro para pantallas de consulta
 * (ej. "consultar-miembro"). A diferencia de MiembroSesionDTO
 * (pensado para la sesión de login), este incluye más campos,
 * pero igualmente nunca expone claveHash.
 */
@Getter
@Setter
@AllArgsConstructor
public class MiembroDetalleDTO {
    private String cedulaMiembro;
    private String nombresCompletos;
    private String apellidosCompletos;
    private String sexo;
    private LocalDate fechaNacimiento;
    private String estadoCuenta;
    private String direccionHabitacion;
    private String correoInstitucional;
    private String telefonoPersonal;
    private LocalDateTime ultimaConexion;
    private BigDecimal indiceRecurrencia;
    private LocalDate fechaApertura;
    private String tipoCategoria; // puede ser null
    private RolMiembro rol;
}