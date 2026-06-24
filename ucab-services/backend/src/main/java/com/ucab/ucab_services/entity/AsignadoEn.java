package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "asignado_en")
@Getter @Setter @NoArgsConstructor
public class AsignadoEn {

    @EmbeddedId
    private AsignadoEnId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "nombre_edif", referencedColumnName = "nombre_edif", insertable = false, updatable = false),
            @JoinColumn(name = "direccion_interna", referencedColumnName = "direccion_interna", insertable = false, updatable = false),
            @JoinColumn(name = "num_identificador", referencedColumnName = "num_identificador", insertable = false, updatable = false)
    })
    private EspacioFisico espacioFisico;

    @ManyToOne
    @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio", insertable = false, updatable = false)
    private Servicio servicio;

}
