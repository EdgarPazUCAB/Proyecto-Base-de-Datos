package com.ucab.ucab_services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MiembroSesionDTO {
    private String cedula;
    private String nombres;
    private String apellidos;
    private String correo;
    private String rol; // <-- Aquí el Frontend en Angular sabrá si es Estudiante, Profesor, etc.
}