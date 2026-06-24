package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @NotBlank(message = "Cédula es requerida")
    @Size(min = 1, max = 20, message = "Cédula debe tener entre 1 y 20 caracteres")
    private String cedula;

    @NotBlank(message = "Clave es requerida")
    @Size(min = 1, message = "La contraseña no puede estar vacía")
    private String clave;

    // Getters and Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}