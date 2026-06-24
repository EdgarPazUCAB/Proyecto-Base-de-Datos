package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auditoria_sesion")
@Getter @Setter @NoArgsConstructor
public class AuditoriaSesion {

    @EmbeddedId
    private AuditoriaSesionId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", insertable = false, updatable = false)
    private Miembro miembro;

    @Column(name = "geolocalizacion", columnDefinition = "TEXT")
    private String geolocalizacion;

    @Column(name = "ip_origen")
    private String ipOrigen; // InetAddress? we'll store as string

    @Column(name = "uuid_dispositivo")
    private java.util.UUID uuidDispositivo;

}