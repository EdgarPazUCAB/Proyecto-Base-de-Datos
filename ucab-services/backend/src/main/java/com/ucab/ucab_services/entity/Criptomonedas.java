package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "criptomonedas")
@Getter
@Setter
@NoArgsConstructor
public class Criptomonedas extends Pago {

    @Column(name = "dxid", length = 100, nullable = false)
    private String dxid;

    @Column(name = "red_blockchain", length = 50, nullable = false)
    private String redBlockchain;

    @Column(name = "billetera", length = 200, nullable = false)
    private String billetera;

    @Column(
        name = "tasa_conversion",
        precision = 18,
        scale = 8,
        nullable = false
    )
    private BigDecimal tasaConversion;
}
