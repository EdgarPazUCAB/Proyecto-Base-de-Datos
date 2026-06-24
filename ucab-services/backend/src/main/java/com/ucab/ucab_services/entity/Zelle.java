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
@Table(name = "zelle")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "numero_control_factura", referencedColumnName = "numero_control_factura")
@PrimaryKeyJoinColumn(name = "fecha_operacion", referencedColumnName = "fecha_operacion")
public class Zelle extends Pago {

    @Column(name = "nombre_titular", length = 200)
    private String nombreTitular;

    @Column(name = "correo_origen", length = 150)
    private String correoOrigen;

    @Column(name = "codigo_confirmacion", length = 50)
    private String codigoConfirmacion;

}