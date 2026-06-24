package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clave primaria compuesta de Edificacion: (Nombre_Edif, Direccion_Interna).
 * JPA exige que las PK compuestas se modelen como una clase @Embeddable
 * que implemente Serializable y tenga equals/hashCode correctos
 * (@EqualsAndHashCode de Lombok se encarga de esto).
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EdificacionId implements Serializable {

    @Column(name = "nombre_edif", length = 100)
    private String nombreEdif;

    @Column(name = "direccion_interna")
    private String direccionInterna;
}