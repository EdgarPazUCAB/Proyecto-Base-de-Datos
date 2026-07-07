package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TarjetaRequest {

    @NotNull(message = "El folioId es obligatorio.")
    private String folioId;

    @NotBlank(message = "El tipo de red es obligatorio.")
    @Pattern(
        regexp = "^(Nacional|Internacional)$",
        message = "El tipo de red debe ser Nacional o Internacional."
    )
    private String tipoRed;

    @NotNull(message = "La fecha de vencimiento es obligatoria.")
    @Future(message = "La tarjeta no puede estar vencida.")
    private java.time.LocalDate fechaVencimiento;

    @NotBlank(message = "La compañía es obligatoria.")
    @Size(max = 100, message = "La compañía no puede exceder 100 caracteres.")
    private String compania;

    @NotBlank(message = "El número de tarjeta es obligatorio.")
    @Size(
        max = 20,
        message = "El número de tarjeta no puede exceder 20 caracteres."
    )
    private String numTarjeta;

    @NotNull(message = "El totalPagado es obligatorio.")
    private Double totalPagado;

    @NotNull(message = "El montoTotalVes es obligatorio.")
    private Double montoTotalVes;
}
