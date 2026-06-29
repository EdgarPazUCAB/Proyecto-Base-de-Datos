package com.ucab.ucab_services.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class PagoMovilRequest {
    @NotNull(message = "El folioId es obligatorio.")
    private String folioId;

    @NotBlank(message = "El banco emisor es obligatorio.")
    @Size(max = 50, message = "El banco no puede exceder 50 caracteres.")
    private String bancoEmisor;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "El teléfono debe contener entre 10 y 11 dígitos.")
    private String telefonoEmisor;

    @NotBlank(message = "La referencia es obligatoria.")
    @Size(min = 4, max = 10, message = "La referencia debe tener entre 4 y 10 caracteres.")
    private String referencia;

    @NotNull(message = "El totalPagado es obligatorio.")
    private Double totalPagado;
}