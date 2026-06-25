package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "zelle")
@Getter @Setter @NoArgsConstructor
public class Zelle extends Pago {

    @Column(name = "correo_titular", length = 100, nullable = false)
    private String correoTitular;

    @Column(name = "referencia_zelle", length = 50, nullable = false)
    private String referenciaZelle;
}