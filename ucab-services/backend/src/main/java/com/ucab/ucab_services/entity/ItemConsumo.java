package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "item_consumo")
@Getter @Setter @NoArgsConstructor
@IdClass(ItemConsumoId.class)
public class ItemConsumo {

    @Id
    @Column(name = "identificador", length = 50, nullable = false, insertable = false, updatable = false)
    private String identificador;

    @Id
    @Column(name = "fecha_apertura", nullable = false, insertable = false, updatable = false)
    private LocalDate fechaApertura;

    @Id
    @Column(name = "concepto", length = 300, nullable = false, insertable = false, updatable = false)
    private String concepto;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "identificador", referencedColumnName = "identificador", insertable = false, updatable = false),
            @JoinColumn(name = "fecha_apertura", referencedColumnName = "fecha_apertura", insertable = false, updatable = false)
    })
    private FolioConsumo folioConsumo;

    @Column(name = "precio_unitario", precision = 18, scale = 4, nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "cantidad", precision = 10, scale = 3, nullable = false)
    private BigDecimal cantidad;

    @Column(name = "impuesto", precision = 5, scale = 2, nullable = false)
    private BigDecimal impuesto;

    @Column(name = "fecha_cargo", nullable = false)
    private LocalDate fechaCargo;

}