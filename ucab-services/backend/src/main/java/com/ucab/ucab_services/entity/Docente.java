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
public class Docente extends Miembro {

    @Column(name = "codigo_investigador", length = 50)
    private String codigoInvestigador;

    @Column(name = "escalafon_docente", length = 100)
    private String escalafonDocente;

    @Column(name = "carga_semanal", precision = 4, scale = 2)
    private java.math.BigDecimal cargaSemanal;

}