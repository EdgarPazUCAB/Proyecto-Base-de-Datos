package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "pago")
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@IdClass(PagoId.class)
public class Pago {

    @Id
    @Column(name = "numero_control_factura", length = 50, nullable = false, insertable = false, updatable = false)
    private String numeroControlFactura;

    @Id
    @Column(name = "fecha_operacion", nullable = false, insertable = false, updatable = false)
    private LocalDateTime fechaOperacion;

    @ManyToOne
    @JoinColumn(name = "numero_control_factura", referencedColumnName = "numero_control", insertable = false, updatable = false)
    private Factura factura;

    @Column(name = "canal_origen", length = 50, nullable = false)
    private String canalOrigen;

    @Column(name = "monto_liquidacion", precision = 18, scale = 4, nullable = false)
    private BigDecimal montoLiquidacion;

    @Column(name = "tipo_pago", length = 20, nullable = false)
    private String tipoPago;

}