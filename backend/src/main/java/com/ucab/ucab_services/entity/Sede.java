package com.ucab.ucab_services.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Mapea la tabla Sede. Entidad de nivel superior de la jerarquía
 * geográfica (Montalbán, Guayana).
 */
@Entity
@Table(name = "sede")
@Getter
@Setter
@NoArgsConstructor
public class Sede {

    @Id
    @jakarta.persistence.Column(name = "nombre_sede", length = 100)
    private String nombreSede;
}