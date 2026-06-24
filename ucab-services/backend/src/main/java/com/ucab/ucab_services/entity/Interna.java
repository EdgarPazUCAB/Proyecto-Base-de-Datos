package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_entidad", referencedColumnName = "id_entidad")
public class Interna extends EntidadPrestadora {

    @Column(name = "tipo_entidad", length = 10, nullable = false, insertable = false, updatable = false)
    private String tipoEntidad;

    @Column(name = "codigo_presu", length = 50, nullable = false, unique = true)
    private String codigoPresu;

    @Column(name = "director_oficina", length = 100)
    private String directorOficina;

}