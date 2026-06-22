package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Mapea la tabla Categoria_Fidelidad.
 * Clasifica a los miembros según su Indice_Recurrencia, otorgando
 * descuentos y prioridad de reservación (ver fn_reclasificar_categoria_fidelidad
 * en la base de datos, que recalcula esto automáticamente).
 */
@Entity
@Table(name = "categoria_fidelidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaFidelidad {

    @Id
    @Column(name = "tipo_categoria", length = 50)
    private String tipoCategoria;

    @Column(name = "rango_indice_min", nullable = false, precision = 4, scale = 2)
    private BigDecimal rangoIndiceMin;

    @Column(name = "rango_indice_max", nullable = false, precision = 4, scale = 2)
    private BigDecimal rangoIndiceMax;

    @Column(name = "descuento_global", precision = 5, scale = 2)
    private BigDecimal descuentoGlobal;

    @Column(name = "prioridad_reservacion")
    private Integer prioridadReservacion;
}