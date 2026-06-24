package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class PersonalAdministrativo extends Miembro {

    @Column(name = "unidad_adscripcion", length = 150)
    private String unidadAdscripcion;

    @Column(name = "cargo_administrativo", length = 150)
    private String cargoAdministrativo;

    @Column(name = "carga_semanal", precision = 4, scale = 2)
    private BigDecimal cargaSemanal;

}