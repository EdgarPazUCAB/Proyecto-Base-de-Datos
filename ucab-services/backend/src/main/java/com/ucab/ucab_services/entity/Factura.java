package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "factura")
@Getter @Setter @NoArgsConstructor
public class Factura {

    @Id
    @Column(name = "numero_control", length = 50, nullable = false)
    private String numeroControl;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador", nullable = false)
    private SolicitudServicio solicitudServicio;

    @ManyToOne
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", nullable = false)
    private Miembro miembro;

    @Column(name = "estatus_factura", length = 30, nullable = false)
    private String estatusFactura;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    @Column(name = "monto_total", precision = 18, scale = 4, nullable = false)
    private BigDecimal montoTotal;

    @Column(name = "saldo_restante_pagar", precision = 18, scale = 4, nullable = false)
    private BigDecimal saldoRestantePagar;

}