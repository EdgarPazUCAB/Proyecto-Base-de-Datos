package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Mapea la tabla Espacio_Fisico. PK compuesta de 3 columnas
 * (Nombre_Edif, Direccion_Interna, Num_identificador), donde las
 * primeras dos también son FK hacia Edificacion.
 *
 * @MapsId (sin "value") indica que TODAS las columnas que el
 * @JoinColumns trae desde Edificacion (nombre_edif y
 * direccion_interna) deben tomarse como parte de la PK ya definida
 * en EspacioFisicoId, en vez de mapearse como columnas nuevas.
 * Usar @MapsId con un solo "value" cuando la FK compuesta cubre DOS
 * columnas de la PK causa que la segunda columna se mapee dos veces
 * (una desde @EmbeddedId, otra desde @JoinColumns) — error real que
 * se confirmó al probarlo: "Column 'direccion_interna' is duplicated".
 */
@Entity
@Table(name = "espacio_fisico")
@Getter
@Setter
@NoArgsConstructor
public class EspacioFisico {

    @EmbeddedId
    private EspacioFisicoId id;

    @ManyToOne
    @MapsId
    @JoinColumns({
            @JoinColumn(name = "nombre_edif", referencedColumnName = "nombre_edif"),
            @JoinColumn(name = "direccion_interna", referencedColumnName = "direccion_interna")
    })
    private Edificacion edificacion;

    @Column(name = "capacidad_aforo", nullable = false)
    private Integer capacidadAforo;

    @Column(name = "tipo_inmobiliario", nullable = false, length = 50)
    private String tipoInmobiliario;

    @Column(name = "estatus", nullable = false, length = 30)
    private String estatus; // 'Disponible', 'Ocupado', 'Mantenimiento'
}