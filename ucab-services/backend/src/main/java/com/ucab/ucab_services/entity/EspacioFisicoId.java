package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class EspacioFisicoId implements Serializable {

    @Column(name = "nombre_edif", length = 100, nullable = false)
    private String nombreEdif;

    @Column(name = "direccion_interna", columnDefinition = "TEXT", nullable = false)
    private String direccionInterna;

    @Column(name = "num_identificador", nullable = false)
    private Integer numIdentificador;

}