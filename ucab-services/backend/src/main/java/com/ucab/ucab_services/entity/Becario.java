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
 * Mapea la tabla Becario. Especialización de Estudiante (no de
 * Miembro directamente) vía composición.
 */
@Entity
@Table(name = "becario")
@Getter @Setter @NoArgsConstructor
public class Becario {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Estudiante estudiante;

    @Column(name = "tipo_beca", length = 50)
    private String tipoBeca;

    @Column(name = "estatus_beneficio", length = 50)
    private String estatusBeneficio;

    @Column(name = "indice_mantenimiento", precision = 4, scale = 2)
    private BigDecimal indiceMantenimiento;

}
