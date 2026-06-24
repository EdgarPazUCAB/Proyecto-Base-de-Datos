package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "espacio_fisico")
@Getter @Setter @NoArgsConstructor
public class EspacioFisico {

    @EmbeddedId
    private EspacioFisicoId id;

    @Column(name = "capacidad_aforo", nullable = false)
    private Integer capacidadAforo;

    @Column(name = "tipo_inmobiliario", length = 50, nullable = false)
    private String tipoInmobiliario;

   @Column(name = "estatus", length = 30, nullable = false)
   private String estatus;

   @ManyToOne
   @JoinColumns({
            @JoinColumn(name = "nombre_edif", referencedColumnName = "nombre_edif", insertable = false, updatable = false),
            @JoinColumn(name = "direccion_interna", referencedColumnName = "direccion_interna", insertable = false, updatable = false)
    })
   private Edificacion edificacion;

}
