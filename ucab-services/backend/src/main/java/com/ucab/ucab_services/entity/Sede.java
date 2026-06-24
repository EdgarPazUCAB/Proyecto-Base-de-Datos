package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sede")
@Getter @Setter @NoArgsConstructor
public class Sede {

    @Id
    @Column(name = "nombre_sede", length = 100, nullable = false)
    private String nombreSede;

}