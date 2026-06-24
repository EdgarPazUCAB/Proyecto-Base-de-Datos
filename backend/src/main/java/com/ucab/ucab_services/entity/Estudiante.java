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

import java.math.BigDecimal;

/**
 * Mapea la tabla Estudiante. Cedula_Miembro es a la vez PK propia y
 * FK hacia Miembro (especialización vía FK, NO INHERITS — ver
 * discusión sobre duplicación de filas en el diseño original).
 *
 * @MapsId indica que el id de esta entidad (cedulaMiembro) se toma
 * directamente del Miembro asociado, en vez de generarse aparte.
 */
@Entity
@Table(name = "estudiante")
@Getter
@Setter
@NoArgsConstructor
public class Estudiante {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Miembro miembro;

    @Column(name = "promedio", precision = 4, scale = 2)
    private BigDecimal promedio;

    @Column(name = "escuela", length = 150)
    private String escuela;

    @Column(name = "estatus_beca", length = 50)
    private String estatusBeca;

    @Column(name = "semestre")
    private Short semestre;

    @Column(name = "facultad", length = 150)
    private String facultad;
}