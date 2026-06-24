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

/**
 * Mapea la tabla Interna. Entidades prestadoras propias de la UCAB
 * (identificadas por código presupuestario y director de oficina).
 */
@Entity
@Table(name = "interna")
@Getter
@Setter
@NoArgsConstructor
public class Interna {

    @Id
    @Column(name = "id_entidad")
    private Integer idEntidad;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_entidad")
    private EntidadPrestadora entidadPrestadora;

    @Column(name = "codigo_presu", nullable = false, unique = true, length = 50)
    private String codigoPresu;

    @Column(name = "director_oficina", length = 100)
    private String directorOficina;
}