package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "oferta_laboral")
@Getter @Setter @NoArgsConstructor
public class OfertaLaboral {

    @EmbeddedId
    private OfertaLaboralId id;

    @ManyToOne
    @JoinColumn(name = "id_entidad_externa", referencedColumnName = "id_entidad", insertable = false, updatable = false)
    private Externa entidadExterna;

    @Column(name = "perfil_buscado", columnDefinition = "TEXT")
    private String perfilBuscado;

    @Column(name = "beneficios", columnDefinition = "TEXT")
    private String beneficios;

    @Column(name = "rango_fecha_graduacion", length = 100)
    private String rangoFechaGraduacion;

    @Column(name = "fecha_oferta")
    private java.sql.Date fechaOferta;

    @Column(name = "responsabilidades", columnDefinition = "TEXT")
    private String responsabilidades;

    @Column(name = "estatus_vacante", length = 50)
    private String estatusVacante;

    @Column(name = "indice_academico_min", precision = 4, scale = 2)
    private Double indiceAcademicoMin;

}