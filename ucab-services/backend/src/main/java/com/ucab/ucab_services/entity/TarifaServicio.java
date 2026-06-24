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
@Table(name = "tarifa_servicio")
@Getter @Setter @NoArgsConstructor
public class TarifaServicio {

    @EmbeddedId
    private TarifaServicioId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio", insertable = false, updatable = false),
            @JoinColumn(name = "perfil_solicitante", referencedColumnName = "perfil_solicitante", insertable = false, updatable = false)
    })
    private Servicio servicio;

    @Column(name = "precio_base", precision = 18, scale = 4, nullable = false)
    private java.math.BigDecimal precioBase;

    @Column(name = "limite_costo", precision = 18, scale = 4)
    private java.math.BigDecimal limiteCosto;

    @Column(name = "fecha_inicio")
    private java.sql.Date fechaInicio;

    @Column(name = "fecha_fin")
    private java.sql.Date fechaFin;

}
