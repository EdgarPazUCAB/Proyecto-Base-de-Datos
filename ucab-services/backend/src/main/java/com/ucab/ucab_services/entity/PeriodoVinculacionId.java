package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Date;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class PeriodoVinculacionId implements Serializable {

    @Column(name = "cedula_miembro", length = 20, nullable = false)
    private String cedulaMiembro;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

}