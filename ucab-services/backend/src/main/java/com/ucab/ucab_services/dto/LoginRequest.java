package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Correo es requerido")
    @Size(max = 100, message = "Correo no puede exceder 100 caracteres")
    private String correo;

    @NotBlank(message = "Clave es requerida")
    @Size(min = 6, max = 100, message = "Clave debe tener entre 6 y 100 caracteres")
    private String clave;
}