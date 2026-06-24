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
 * Clave primaria compuesta de Espacio_Fisico:
 * (Nombre_Edif, Direccion_Interna, Num_identificador).
 *
 * NOTA: Num_identificador es SERIAL en PostgreSQL (autoincremental),
 * pero al formar parte de una PK compuesta, Hibernate no puede
 * generarlo automáticamente desde Java en este modelo — el valor lo
 * asigna PostgreSQL al insertar. Por eso al crear un Espacio_Fisico
 * nuevo desde el backend, este campo se deja en null en el INSERT
 * y se relee después si se necesita conocer el valor generado
 * (ver EspacioFisicoService para el patrón exacto).
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EspacioFisicoId implements Serializable {

    @Column(name = "nombre_edif", length = 100)
    private String nombreEdif;

    @Column(name = "direccion_interna")
    private String direccionInterna;

    @Column(name = "num_identificador")
    private Integer numIdentificador;
}