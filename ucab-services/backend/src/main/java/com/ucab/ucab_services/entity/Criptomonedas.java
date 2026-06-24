package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "criptomonedas")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "numero_control_factura", referencedColumnName = "numero_control_factura")
@PrimaryKeyJoinColumn(name = "fecha_operacion", referencedColumnName = "fecha_operacion")
public class Criptomonedas extends Pago {

    @Column(name = "dxid", length = 100)
    private String dxid;

    @Column(name = "red_blockchain", length = 50)
    private String redBlockchain;

    @Column(name = "billetera", length = 200)
    private String billetera;

    @Column(name = "tasa_conversion", precision = 18, scale = 8)
    private java.math.BigDecimal tasaConversion;

}