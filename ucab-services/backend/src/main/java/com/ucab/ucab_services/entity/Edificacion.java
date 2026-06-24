package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "edificacion")
@Getter @Setter @NoArgsConstructor
public class Edificacion {

    @EmbeddedId
    private EdificacionId id;

    @ManyToOne
    @JoinColumn(name = "nombre_sede", referencedColumnName = "nombre_sede", insertable = false, updatable = false)
    private Sede sede;

}