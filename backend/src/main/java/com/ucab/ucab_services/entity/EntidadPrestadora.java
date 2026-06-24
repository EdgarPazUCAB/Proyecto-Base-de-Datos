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
 * Interna/Externa (mismo patrón de FK simple que Miembro — sin
 * INHERITS, para evitar duplicación de filas al consultar).
 *
 * tipoEntidad discrimina cuál subtipo corresponde, pero la validación
 * fuerte (que una fila Interna no pueda referenciar una Entidad_Prestadora
 * marcada como 'Externa') vive en la base de datos vía el constraint
 * compuesto fk_interna_padre / fk_externa_padre — no se repite en Java.
 */
@Entity
@Table(name = "entidad_prestadora")
@Getter
@Setter
@NoArgsConstructor
public class EntidadPrestadora {

    @Id
    @Column(name = "id_entidad")
    private Integer idEntidad;

    @Column(name = "tipo_entidad", nullable = false, length = 10)
    private String tipoEntidad; // 'Interna' o 'Externa'
}