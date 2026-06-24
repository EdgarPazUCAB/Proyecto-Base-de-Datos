package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "preparador")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class Preparador extends Estudiante {

    @Column(name = "asignatura_asignada", length = 150)
    private String asignaturaAsignada;

    @Column(name = "horas_ayudantia", precision = 5, scale = 2)
    private Double horasAyudantia;

}