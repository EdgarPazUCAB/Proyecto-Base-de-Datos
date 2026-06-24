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
public class ClasificadoEnId implements Serializable {

    @Column(name = "perfil_solicitante", length = 30, nullable = false)
    private String perfilSolicitante;

    @Column(name = "codigo_servicio", length = 30, nullable = false)
    private String codigoServicio;

    @Column(name = "tipo_categoria", length = 50, nullable = false)
    private String tipoCategoria;

}