package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "solicitud_servicio")
@Getter @Setter @NoArgsConstructor
public class SolicitudServicio {

    @Id
    @Column(name = "identificador", length = 30, nullable = false)
    private String identificador;

    @ManyToOne
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", nullable = false)
    private Miembro miembro;

    @ManyToOne
    @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio", nullable = false)
    private Servicio servicio;

    @Column(name = "estado_actual", length = 50, nullable = false)
    private String estadoActual;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // We'll add a transient field for tiempo_resolucion if needed, but it's computed by a function in DB.
    // We can ignore it for now or add a method to compute it.

}