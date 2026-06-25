package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pago_movil")
@Getter @Setter @NoArgsConstructor
public class PagoMovil extends Pago {

    @Column(name = "telefono_origen", length = 20, nullable = false)
    private String telefonoOrigen;

    @Column(name = "banco_destino", length = 100, nullable = false)
    private String bancoDestino;

    @Column(name = "referencia_pm", length = 50, nullable = false)
    private String referenciaPm;
}