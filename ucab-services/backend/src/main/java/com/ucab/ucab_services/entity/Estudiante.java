package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Mapea la tabla Estudiante. Especialización de Miembro vía
 * composición (@OneToOne + @MapsId), NO herencia de Java — mismo
 * estándar usado en EntidadPrestadora/Interna/Externa.
 */
@Entity
@Getter @Setter @NoArgsConstructor
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
    private Integer semestre;

    @Column(name = "facultad", length = 150)
    private String facultad;

}
