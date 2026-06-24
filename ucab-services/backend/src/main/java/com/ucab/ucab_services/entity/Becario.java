package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "becario")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class Becario extends Estudiante {

    @Column(name = "tipo_beca", length = 50)
    private String tipoBeca;

    @Column(name = "estatus_beneficio", length = 50)
    private String estatusBeneficio;

    @Column(name = "indice_mantenimiento", precision = 4, scale = 2)
    private BigDecimal indiceMantenimiento;

}
