package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class PagoId implements Serializable {

    @Column(name = "numero_control_factura", length = 50, nullable = false)
    private String numeroControlFactura;

    @Column(name = "fecha_operacion", nullable = false)
    private LocalDateTime fechaOperacion;

    // AÑADIDO: Ahora tipo_pago forma parte de la clave primaria compuesta
    @Column(name = "tipo_pago", length = 20, nullable = false)
    private String tipoPago;
}