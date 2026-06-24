package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class Estudiante extends Miembro {

    @Column(name = "promedio", precision = 4, scale = 2)
    private Double promedio;

    @Column(name = "escuela", length = 150)
    private String escuela;

    @Column(name = "estatus_beca", length = 50)
    private String estatusBeca;

    @Column(name = "semestre")
    private Integer semestre;

    @Column(name = "facultad", length = 150)
    private String facultad;

}