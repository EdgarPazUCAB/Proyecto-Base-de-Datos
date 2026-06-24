package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MiembroSesionDTO {
    @NotBlank(message = "Cédula es requerida")
    @Size(min = 1, max = 20, message = "Cédula debe tener entre 1 y 20 caracteres")
    private String cedula;

    @NotBlank(message = "Nombre completo es requerido")
    @Size(min = 2, max = 200, message = "Nombre completo debe tener entre 2 y 200 caracteres")
    private String nombreCompleto;

    @NotBlank(message = "Correo es requerido")
    @Size(min = 5, max = 150, message = "Correo debe tener entre 5 y 150 caracteres")
    private String correo;

    @NotBlank(message = "Estado de cuenta es requerido")
    @Size(min = 1, max = 30, message = "Estado de cuenta debe tener entre 1 y 30 caracteres")
    private String estadoCuenta;

    // Constructors
    public MiembroSesionDTO() {}

    public MiembroSesionDTO(String cedula, String nombreCompleto, String correo, String estadoCuenta) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.estadoCuenta = estadoCuenta;
    }

    // Getters and Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }
}