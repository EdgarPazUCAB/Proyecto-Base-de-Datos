package com.ucab.ucab_services.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class TaiRequest {
    @NotNull(message = "El folioId es obligatorio.")
    private String folioId;

    @NotBlank(message = "El POS Terminal es obligatorio.")
    @Size(max = 50, message = "El POS Terminal no puede exceder 50 caracteres.")
    private String posTerminal;

    @NotBlank(message = "El recibo digital es obligatorio.")
    private String reciboDigital;

    @NotNull(message = "El total pagado es obligatorio.")
    private Double totalPagado;
}
