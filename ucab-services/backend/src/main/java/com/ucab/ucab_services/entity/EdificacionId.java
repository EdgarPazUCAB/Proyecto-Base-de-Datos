package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class EdificacionId implements Serializable {

    @Column(name = "nombre_edif", length = 100, nullable = false)
    private String nombreEdif;
    
    @Column(name = "direccion_interna", columnDefinition = "TEXT", nullable = false)
    private String direccionInterna;

}