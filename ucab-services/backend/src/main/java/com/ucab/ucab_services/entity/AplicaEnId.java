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
public class AplicaEnId implements Serializable {

    @Column(name = "nombre_sede", length = 100, nullable = false)
    private String nombreSede;

    @Column(name = "codigo_servicio", length = 50, nullable = false)
    private String codigoServicio;

    @Column(name = "perfil_solicitante", length = 100, nullable = false)
    private String perfilSolicitante;

}