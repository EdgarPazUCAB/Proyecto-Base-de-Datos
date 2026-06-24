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
@Table(name = "acompanante_temporal")
@Getter @Setter @NoArgsConstructor
public class AcompananteTemporal {

    @Id
    @Column(name = "documento_identidad_acom", length = 30, nullable = false)
    private String documentoIdentidadAcom;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador", nullable = false)
    private SolicitudServicio solicitudServicio;

    @Column(name = "nombre_acompanante", length = 200, nullable = false)
    private String nombreAcompanante;

    @Column(name = "estado_activo", nullable = false)
    private Boolean estadoActivo;

}