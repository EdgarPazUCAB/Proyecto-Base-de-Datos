package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Mapea la tabla Entidad_Prestadora. Tabla padre de la especialización
 * Interna/Externa vía composición (@OneToOne + @MapsId en los
 * subtipos), NO herencia de Java — mismo estándar usado en Miembro,
 * para mantener consistencia en todo el proyecto.
 */
@Entity
@Table(name = "entidad_prestadora")
@Getter @Setter @NoArgsConstructor
public class EntidadPrestadora {

    @Id
    @Column(name = "id_entidad", nullable = false)
    private Integer idEntidad;

    @Column(name = "tipo_entidad", length = 10, nullable = false)
    private String tipoEntidad;

}