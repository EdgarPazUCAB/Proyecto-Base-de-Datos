package com.ucab.ucab_services.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class Egresado extends Miembro {

    @Column(name = "titulo", length = 200)
    private String titulo;

    @Column(name = "ano_graduacion")
    private Integer anoGraduacion;

    @Column(name = "indice_academico", precision = 4, scale = 2)
    private java.math.BigDecimal indiceAcademico;

}