package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categoria_fidelidad")
@Getter @Setter @NoArgsConstructor
public class CategoriaFidelidad {

    @Id
    @Column(name = "tipo_categoria", length = 50, nullable = false, unique = true)
    private String tipoCategoria;

    @Column(name = "rango_indice_min", precision = 4, scale = 2, nullable = false)
    private java.math.BigDecimal rangoIndiceMin;

    @Column(name = "rango_indice_max", precision = 4, scale = 2, nullable = false)
    private java.math.BigDecimal rangoIndiceMax;

    @Column(name = "descuento_global", precision = 5, scale = 2, nullable = false)
    private java.math.BigDecimal descuentoGlobal;

    @Column(name = "prioridad_reservacion", nullable = false)
    private Integer prioridadReservacion;

}