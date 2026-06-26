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
 * Mapea la tabla Egresado. Especialización de Miembro vía
 * composición. Datos permanentes que coexisten con cualquier nueva
 * vinculación que el miembro inicie en el futuro.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Egresado {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Miembro miembro;

    @Column(name = "titulo", length = 200)
    private String titulo;

    @Column(name = "ano_graduacion")
    private Integer anoGraduacion;

    @Column(name = "indice_academico", precision = 4, scale = 2)
    private BigDecimal indiceAcademico;

}
