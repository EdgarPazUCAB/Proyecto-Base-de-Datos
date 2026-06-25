package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "efectivo")
@Getter @Setter @NoArgsConstructor
public class Efectivo extends Pago {

    @Column(name = "denominacion_billetes", columnDefinition = "TEXT")
    private String denominacionBilletes;
}