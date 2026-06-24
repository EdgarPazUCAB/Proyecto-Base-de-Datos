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
@Table(name = "pago_tai")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "numero_control_factura", referencedColumnName = "numero_control_factura")
@PrimaryKeyJoinColumn(name = "fecha_operacion", referencedColumnName = "fecha_operacion")
public class PagoTai extends Pago {

    @Column(name = "pos_terminal", length = 50)
    private String posTerminal;

    @Column(name = "recibo_digital", columnDefinition = "TEXT")
    private String reciboDigital;

    @Column(name = "saldo_restante", precision = 18, scale = 4)
    private java.math.BigDecimal saldoRestante;

    @Column(name = "uid_billetera", nullable = false)
    private java.util.UUID uidBilletera;

}