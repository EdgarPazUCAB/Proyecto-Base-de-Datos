package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class AuditoriaSesionId implements Serializable {

    @Column(name = "cedula_miembro", length = 20, nullable = false)
    private String cedulaMiembro;

    @Column(name = "fecha_hora_acceso", nullable = false)
    private Timestamp fechaHoraAcceso;

}