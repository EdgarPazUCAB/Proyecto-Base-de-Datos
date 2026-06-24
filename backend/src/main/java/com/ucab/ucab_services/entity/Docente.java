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
 * Mapea la tabla Docente.
 */
@Entity
@Table(name = "docente")
@Getter
@Setter
@NoArgsConstructor
public class Docente {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Miembro miembro;

    @Column(name = "codigo_investigador", length = 50)
    private String codigoInvestigador; // null si no es investigador

    @Column(name = "escalafon_docente", length = 100)
    private String escalafonDocente;

    @Column(name = "carga_semanal", precision = 4, scale = 2)
    private BigDecimal cargaSemanal;
}