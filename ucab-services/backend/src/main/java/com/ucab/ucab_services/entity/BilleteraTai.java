package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "billetera_tai")
@Getter @Setter @NoArgsConstructor
public class BilleteraTai {

    @Id
    @Column(name = "uid", nullable = false, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uid;

    @OneToOne(optional = false)
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", nullable = false, unique = true)
    private Miembro miembro;

    @Column(name = "saldo_virtual", precision = 18, scale = 4, nullable = false)
    private BigDecimal saldoVirtual = BigDecimal.ZERO;

    @Column(name = "saldo_restante", precision = 18, scale = 4, nullable = false)
    private BigDecimal saldoRestante = BigDecimal.ZERO;

}