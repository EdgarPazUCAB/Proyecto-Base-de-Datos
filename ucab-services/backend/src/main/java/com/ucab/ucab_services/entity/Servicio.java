package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servicio")
@Getter @Setter @NoArgsConstructor
public class Servicio {

    @Id
    @Column(name = "codigo_servicio", length = 50, nullable = false)
    private String codigoServicio;

    @Column(name = "descripcion_detallada", columnDefinition = "TEXT")
    private String descripcionDetallada;

    @Column(name = "requisitos", columnDefinition = "TEXT")
    private String requisitos;

    @Column(name = "estado_servicio", length = 50, nullable = false)
    private String estadoServicio;

    @Column(name = "perfil_solicitante", columnDefinition = "TEXT")
    private String perfilSolicitante;

    @ManyToOne
    @JoinColumn(name = "id_entidad", referencedColumnName = "id_entidad", nullable = false)
    private EntidadPrestadora entidadPrestadora;

}