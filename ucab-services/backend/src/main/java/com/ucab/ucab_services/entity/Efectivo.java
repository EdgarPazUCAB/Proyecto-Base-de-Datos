package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "efectivo")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "numero_control_factura", referencedColumnName = "numero_control_factura")
@PrimaryKeyJoinColumn(name = "fecha_operacion", referencedColumnName = "fecha_operacion")
public class Efectivo extends Pago {

    @Column(name = "desglose_denominacion")
    private String desgloseDenominacion;

    @Column(name = "divisa", length = 10)
    private String divisa;

    @Column(name = "tasa_bcv", precision = 18, scale = 8)
    private java.math.BigDecimal tasaBcv;

}