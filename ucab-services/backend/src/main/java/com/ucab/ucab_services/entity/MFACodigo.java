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

import java.sql.Timestamp;

@Entity
@Table(name = "mfa_codigo")
@Getter @Setter @NoArgsConstructor
public class MFACodigo {

    @EmbeddedId
    private MFACodigoId id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", insertable = false, updatable = false)
    private Miembro miembro;

    @Column(name = "codigo", length = 6, nullable = false)
    private String codigo;

    @Column(name = "fecha_expira", nullable = false)
    private Timestamp fechaExpira;

    @Column(name = "usado", nullable = false)
    private Boolean usado;

}