package com.ucab.ucab_services.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ZelleRequest {
    @NotNull(message = "El folioId es obligatorio.")
    private String folioId;

    @NotBlank(message = "El nombre del titular es obligatorio.")
    @Size(max = 200, message = "El nombre del titular no puede exceder 200 caracteres.")
    private String nombreTitular;

    @NotBlank(message = "El correo de origen es obligatorio.")
    @Email(message = "Debe proporcionar un correo válido.")
    @Size(max = 150, message = "El correo no puede exceder 150 caracteres.")
    private String correoOrigen;

    @NotBlank(message = "El código de confirmación es obligatorio.")
    @Size(max = 50, message = "El código de confirmación no puede exceder 50 caracteres.")
    private String codigoConfirmacion;

    @NotNull(message = "El total pagado es obligatorio.")
    private Double totalPagado; // This should be in VES for the backend to record

    @NotNull(message = "El montoTotalVes es obligatorio.")
    private Double montoTotalVes;
}
