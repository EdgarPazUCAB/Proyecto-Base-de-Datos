package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Mapea la tabla Tarifa_Servicio. PK compuesta (Perfil_solicitante,
 * Codigo_Servicio) representada por TarifaServicioId.
 *
 * Codigo_Servicio es a la vez parte de la PK y FK hacia Servicio —
 * por eso @MapsId aquí cubre solo esa columna (a diferencia de
 * EspacioFisico, donde la FK cubría DOS columnas de la PK).
 * Perfil_solicitante no es FK a ninguna tabla, es solo un valor
 * textual libre ('Miembro_Activo', 'Egresado', 'Externo').
 */
@Entity
@Table(name = "tarifa_servicio")
@Getter
@Setter
@NoArgsConstructor
public class TarifaServicio {

    @EmbeddedId
    private TarifaServicioId id;

    @ManyToOne
    @MapsId("codigoServicio")
    @JoinColumn(name = "codigo_servicio")
    private Servicio servicio;

    @Column(name = "precio_base", nullable = false, precision = 18, scale = 4)
    private BigDecimal precioBase;

    @Column(name = "limite_costo", precision = 18, scale = 4)
    private BigDecimal limiteCosto;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
}