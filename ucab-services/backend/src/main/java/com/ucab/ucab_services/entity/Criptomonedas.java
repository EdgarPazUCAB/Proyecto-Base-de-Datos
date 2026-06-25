package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "criptomonedas")
@Getter @Setter @NoArgsConstructor
public class Criptomonedas extends Pago {

    @Column(name = "dxid", length = 100, nullable = false)
    private String dxid;

    @Column(name = "red_blockchain", length = 50, nullable = false)
    private String redBlockchain;

    @Column(name = "billetera", length = 100, nullable = false)
    private String billetera;

    @Column(name = "tasa_conversion", precision = 10, scale = 2, nullable = false)
    private BigDecimal tasaConversion;
}