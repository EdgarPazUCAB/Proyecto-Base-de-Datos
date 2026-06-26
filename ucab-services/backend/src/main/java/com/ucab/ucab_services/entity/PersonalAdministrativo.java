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
 * Mapea la tabla Personal_Administrativo. Especialización de Miembro
 * vía composición.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class PersonalAdministrativo {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Miembro miembro;

    @Column(name = "unidad_adscripcion", length = 150)
    private String unidadAdscripcion;

    @Column(name = "cargo_administrativo", length = 150)
    private String cargoAdministrativo;

    @Column(name = "carga_semanal", precision = 4, scale = 2)
    private BigDecimal cargaSemanal;

}
