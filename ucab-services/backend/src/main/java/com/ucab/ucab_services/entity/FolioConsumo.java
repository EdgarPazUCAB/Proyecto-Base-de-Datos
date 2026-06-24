package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "folio_consumo")
@Getter @Setter @NoArgsConstructor
@IdClass(FolioConsumoId.class)
public class FolioConsumo {

    @Id
    @Column(name = "identificador", length = 50, nullable = false, insertable = false, updatable = false)
    private String identificador;

    @Id
    @Column(name = "fecha_apertura", nullable = false, insertable = false, updatable = false)
    private LocalDate fechaApertura;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador", insertable = false, updatable = false)
    private SolicitudServicio solicitudServicio;

    @Column(name = "estado_cierre", length = 50, nullable = false)
    private String estadoCierre;

}