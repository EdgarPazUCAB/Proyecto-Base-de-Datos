package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarjeta")
@Getter @Setter @NoArgsConstructor
public class Tarjeta extends Pago {

    @Column(name = "tipo_tarjeta", length = 20, nullable = false)
    private String tipoTarjeta;

    @Column(name = "banco_emisor", length = 100, nullable = false)
    private String bancoEmisor;

    @Column(name = "ultimos_cuatro", length = 4, nullable = false)
    private String ultimosCuatro;
}