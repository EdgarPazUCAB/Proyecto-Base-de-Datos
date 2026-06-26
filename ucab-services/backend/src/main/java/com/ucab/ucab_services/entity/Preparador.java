package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Mapea la tabla Preparador. Especialización de Estudiante (no de
 * Miembro directamente) vía composición — su PK es FK hacia
 * Estudiante.cedula_miembro, requiere que esa fila ya exista.
 */
@Entity
@Table(name = "preparador")
@Getter @Setter @NoArgsConstructor
public class Preparador {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Estudiante estudiante;

    @Column(name = "asignatura_asignada", length = 150)
    private String asignaturaAsignada;

    @Column(name = "horas_ayudantia", precision = 5, scale = 2)
    private BigDecimal horasAyudantia;

}
