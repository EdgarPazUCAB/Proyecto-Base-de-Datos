package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PasoActividadId implements Serializable {

    @Column(name = "identificador", length = 30, nullable = false)
    private String identificador;

    @Column(name = "orden_paso", nullable = false)
    private Integer ordenPaso;
}