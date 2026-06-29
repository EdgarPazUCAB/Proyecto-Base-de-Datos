package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Mapea la tabla Pago_Movil. Columnas reales en SQL: banco,
 * referencia, telefono_emisor, fecha_movimiento (ver
 * ucabservices_completo.sql).
 *
 * "banco" se interpreta como el banco EMISOR (de dónde sale el
 * pago) — el banco destino es siempre el de la UCAB, un dato
 * institucional fijo que no se captura por transacción, coherente
 * con el enunciado: "el cajero debe ingresar el número de teléfono
 * emisor, el banco de origen, el número de referencia...".
 */
@Entity
@Table(name = "pago_movil")
@Getter @Setter @NoArgsConstructor
public class PagoMovil extends Pago {

    @Column(name = "telefono_emisor", length = 20, nullable = false)
    private String telefonoEmisor;

    @Column(name = "banco", length = 100, nullable = false)
    private String banco;

    @Column(name = "referencia", length = 100, nullable = false)
    private String referencia;

    @Column(name = "fecha_movimiento")
    private LocalDate fechaMovimiento;
}