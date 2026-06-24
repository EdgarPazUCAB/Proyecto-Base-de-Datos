package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "periodo_vinculacion")
@Getter @Setter @NoArgsConstructor
public class PeriodoVinculacion {

    @EmbeddedId
    private PeriodoVinculacionId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", insertable = false, updatable = false)
    private Miembro miembro;

    @Column(name = "rol", length = 30, nullable = false)
    private String rol;

    @Column(name = "fecha_fin")
    private Date fechaFin;

}