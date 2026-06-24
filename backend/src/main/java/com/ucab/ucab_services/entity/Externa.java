package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Mapea la tabla Externa. Aliados comerciales o concesionarios
 * externos a la UCAB (RIF, razón social, contrato de concesión).
 */
@Entity
@Table(name = "externa")
@Getter
@Setter
@NoArgsConstructor
public class Externa {

    @Id
    @Column(name = "id_entidad")
    private Integer idEntidad;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_entidad")
    private EntidadPrestadora entidadPrestadora;

    @Column(name = "rif", nullable = false, unique = true, length = 50)
    private String rif;

    @Column(name = "razon_social", length = 150)
    private String razonSocial;

    @Column(name = "contactos_legales")
    private String contactosLegales;

    @Column(name = "fecha_vencimiento_contrato")
    private LocalDate fechaVencimientoContrato;
}