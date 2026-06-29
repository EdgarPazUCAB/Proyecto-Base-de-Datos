package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarjeta")
@Getter @Setter @NoArgsConstructor
public class Tarjeta extends Pago {

    @Column(name = "tipo_red", length = 50)
    private String tipoRed;

    @Column(name = "fecha_vencimiento")
    private java.time.LocalDate fechaVencimiento;

    @Column(name = "compania", length = 100)
    private String compania;

    @Column(name = "num_tarjeta", length = 20)
    private String numTarjeta;
}
