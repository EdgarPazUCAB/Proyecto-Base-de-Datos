package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "zelle")
@Getter
@Setter
@NoArgsConstructor
public class Zelle extends Pago {

    @Column(name = "nombre_titular", length = 200, nullable = false)
    private String nombreTitular;

    @Column(name = "correo_origen", length = 150, nullable = false)
    private String correoOrigen;

    @Column(name = "codigo_confirmacion", length = 50, nullable = false)
    private String codigoConfirmacion;
}
