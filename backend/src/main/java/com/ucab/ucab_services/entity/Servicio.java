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

/**
 * Mapea la tabla Servicio. Publicado por una EntidadPrestadora
 * (interna o externa).
 */
@Entity
@Table(name = "servicio")
@Getter
@Setter
@NoArgsConstructor
public class Servicio {

    @Id
    @Column(name = "codigo_servicio", length = 50)
    private String codigoServicio;

    @Column(name = "descripcion_detallada")
    private String descripcionDetallada;

    @Column(name = "requisitos")
    private String requisitos;

    @Column(name = "estado_servicio", length = 50)
    private String estadoServicio; // 'Activo', 'Inactivo', 'Suspendido'

    @Column(name = "perfil_solicitante")
    private String perfilSolicitante;

    @ManyToOne
    @JoinColumn(name = "id_entidad", nullable = false)
    private EntidadPrestadora entidadPrestadora;
}