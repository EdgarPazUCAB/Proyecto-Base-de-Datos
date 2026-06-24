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
@Table(name = "clasificado_en")
@Getter @Setter @NoArgsConstructor
public class ClasificadoEn {

    @EmbeddedId
    private ClasificadoEnId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "perfil_solicitante", referencedColumnName = "perfil_solicitante", insertable = false, updatable = false),
            @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio", insertable = false, updatable = false)
    })
    private TarifaServicio tarifaServicio;

    @ManyToOne
    @JoinColumn(name = "tipo_categoria", referencedColumnName = "tipo_categoria", insertable = false, updatable = false)
    private CategoriaFidelidad categoriaFidelidad;

}
