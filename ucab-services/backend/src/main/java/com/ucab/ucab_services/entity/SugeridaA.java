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
@Table(name = "sugerida_a")
@Getter @Setter @NoArgsConstructor
public class SugeridaA {

    @EmbeddedId
    private SugeridaAId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_entidad_externa", referencedColumnName = "id_entidad_externa"),
            @JoinColumn(name = "cargo_laboral", referencedColumnName = "cargo_laboral")
    })
    private OfertaLaboral ofertaLaboral;

    @ManyToOne
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
    private Egresado egresado;

}