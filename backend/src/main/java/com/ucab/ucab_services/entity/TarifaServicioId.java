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
 * Clave primaria compuesta de Tarifa_Servicio: (Perfil_solicitante, Codigo_Servicio).
 * A diferencia de EspacioFisicoId, ninguna de las dos columnas es
 * autogenerada — ambas se proveen al crear la tarifa, así que esta
 * PK no tiene el riesgo de @MapsId parcial que vimos en EspacioFisico.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TarifaServicioId implements Serializable {

    @Column(name = "perfil_solicitante", length = 30)
    private String perfilSolicitante;

    @Column(name = "codigo_servicio", length = 30)
    private String codigoServicio;
}