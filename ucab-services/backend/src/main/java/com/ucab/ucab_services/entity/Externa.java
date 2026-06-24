package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_entidad", referencedColumnName = "id_entidad")
public class Externa extends EntidadPrestadora {

    @Column(name = "tipo_entidad", length = 10, nullable = false, insertable = false, updatable = false)
    private String tipoEntidad;

    @Column(name = "rif", length = 50, nullable = false, unique = true)
    private String rif;

    @Column(name = "razon_social", length = 150)
    private String razonSocial;

    @Column(name = "contactos_legales", columnDefinition = "TEXT")
    private String contactosLegales;

    @Column(name = "fecha_vencimiento_contrato")
    private java.sql.Date fechaVencimientoContrato;

}