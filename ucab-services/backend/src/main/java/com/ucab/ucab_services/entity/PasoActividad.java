package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "paso_actividad")
@Getter @Setter @NoArgsConstructor
@IdClass(PasoActividadId.class)
public class PasoActividad {

    @Id
    @Column(name = "identificador", length = 30, nullable = false, insertable = false, updatable = false)
    private String identificador;

    @Id
    @Column(name = "orden_paso", nullable = false, insertable = false, updatable = false)
    private Integer ordenPaso;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador", insertable = false, updatable = false)
    private SolicitudServicio solicitudServicio;

    @Column(name = "estado_paso", length = 50, nullable = false)
    private String estadoPaso;

    @Column(name = "fecha_evento")
    private LocalDate fechaEvento;

    @Column(name = "responsable", length = 200)
    private String responsable;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

}