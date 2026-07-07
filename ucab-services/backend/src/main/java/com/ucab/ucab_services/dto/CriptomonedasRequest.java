package com.ucab.ucab_services.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class CriptomonedasRequest {
    @NotNull(message = "El folioId es obligatorio.")
    private String folioId;

    @NotBlank(message = "El txid es obligatorio.")
    @Size(max = 100, message = "El txid no puede exceder 100 caracteres.")
    private String dxid;

    @NotBlank(message = "La red blockchain es obligatoria.")
    @Size(max = 50, message = "La red no puede exceder 50 caracteres.")
    private String redBlockchain;

    @NotBlank(message = "La billetera es obligatoria.")
    @Size(max = 200, message = "La billetera no puede exceder 200 caracteres.")
    private String billetera;

    @NotNull(message = "La tasa de conversión es obligatoria.")
    private Double tasaConversion;

    @NotNull(message = "El totalPagado es obligatorio.")
    private Double totalPagado;

    @NotNull(message = "El montoTotalVes es obligatorio.")
    private Double montoTotalVes;
}
