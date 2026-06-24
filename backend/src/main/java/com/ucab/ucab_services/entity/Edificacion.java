package com.ucab.ucab_services.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Mapea la tabla Edificacion. PK compuesta (Nombre_Edif, Direccion_Interna)
 * representada por EdificacionId.
 *
 * @MapsId("nombreSede") en este caso NO aplica directamente porque
 * Sede no forma parte de la PK de Edificacion — nombre_sede es solo
 * una FK normal hacia Sede, no parte de la clave compuesta. Por eso
 * usamos @ManyToOne normal (sin MapsId) para esa relación.
 */
@Entity
@Table(name = "edificacion")
@Getter
@Setter
@NoArgsConstructor
public class Edificacion {

    @EmbeddedId
    private EdificacionId id;

    @ManyToOne
    @JoinColumn(name = "nombre_sede", referencedColumnName = "nombre_sede", nullable = false)
    private Sede sede;
}