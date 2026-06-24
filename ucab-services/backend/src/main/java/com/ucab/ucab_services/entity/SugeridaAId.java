package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class SugeridaAId implements Serializable {

    @Column(name = "id_entidad_externa", nullable = false)
    private Integer idEntidadExterna;

    @Column(name = "cargo_laboral", length = 100, nullable = false)
    private String cargoLaboral;

    @Column(name = "cedula_miembro", length = 20, nullable = false)
    private String cedulaMiembro;

}