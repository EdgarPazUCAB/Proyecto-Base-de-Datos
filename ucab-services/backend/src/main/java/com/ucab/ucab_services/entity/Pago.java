package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Inheritance(strategy = InheritanceType.JOINED)
@IdClass(PagoId.class)
@Getter @Setter @NoArgsConstructor
public class Pago {

    @Id
    @Column(name = "numero_control_factura", length = 50, nullable = false) // Sin insertable=false
    private String numeroControlFactura;

    @Id
    @Column(name = "fecha_operacion", nullable = false) // Sin insertable=false
    private LocalDateTime fechaOperacion;

    @Id
    @Column(name = "tipo_pago", length = 20, nullable = false)
    private String tipoPago;

    @ManyToOne
    @JoinColumn(name = "numero_control_factura", referencedColumnName = "numero_control", insertable = false, updatable = false)
    private Factura factura;

    @Column(name = "canal_origen", length = 50, nullable = false)
    private String canalOrigen;

    @Column(name = "monto_liquidacion", precision = 18, scale = 4, nullable = false)
    private BigDecimal montoLiquidacion;
}