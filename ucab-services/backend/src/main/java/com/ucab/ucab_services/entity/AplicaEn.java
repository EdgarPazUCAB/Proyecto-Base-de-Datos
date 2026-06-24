package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aplica_en")
@Getter @Setter @NoArgsConstructor
public class AplicaEn {

    @EmbeddedId
    private AplicaEnId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio"),
            @JoinColumn(name = "perfil_solicitante", referencedColumnName = "perfil_solicitante")
    })
    private TarifaServicio tarifaServicio;

    @Column(name = "precio_base", precision = 10, scale = 2, nullable = false)
    private java.math.BigDecimal precioBase;

    @Column(name = "limite_costo", precision = 10, scale = 2)
    private java.math.BigDecimal limiteCosto;

}