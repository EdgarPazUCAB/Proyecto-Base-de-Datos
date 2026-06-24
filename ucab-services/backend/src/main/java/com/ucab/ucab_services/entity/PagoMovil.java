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
@Table(name = "pago_movil")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "numero_control_factura", referencedColumnName = "numero_control_factura")
@PrimaryKeyJoinColumn(name = "fecha_operacion", referencedColumnName = "fecha_operacion")
public class PagoMovil extends Pago {

    @Column(name = "banco", length = 100)
    private String banco;

    @Column(name = "referencia", length = 100)
    private String referencia;

    @Column(name = "telefono_emisor", length = 20)
    private String telefonoEmisor;

    @Column(name = "fecha_movimiento")
    private java.sql.Date fechaMovimiento;

}