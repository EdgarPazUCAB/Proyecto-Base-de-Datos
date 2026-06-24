package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VerificarMfaRequest {
    @NotBlank(message = "Cédula es requerida")
    @Size(min = 1, max = 20, message = "Cédula debe tener entre 1 y 20 caracteres")
    private String cedula;

    @NotBlank(message = "Código es requerido")
    @Size(min = 6, max = 6, message = "Código debe tener exactamente 6 caracteres")
    private String codigo;

    // Getters and Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}