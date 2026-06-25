package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "pago_tai")
@Getter @Setter @NoArgsConstructor
public class PagoTai extends Pago {

    @Column(name = "pos_terminal", length = 50, nullable = false)
    private String posTerminal;

    @Column(name = "recibo_digital", length = 255, nullable = false)
    private String reciboDigital;

    @Column(name = "saldo_restante", precision = 10, scale = 2, nullable = false)
    private BigDecimal saldoRestante;

    @Column(name = "uid_billetera", length = 50, nullable = false)
    private String uidBilletera;
}