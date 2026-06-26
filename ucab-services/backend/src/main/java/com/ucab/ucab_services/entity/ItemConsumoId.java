    package com.ucab.ucab_services.entity;

    import jakarta.persistence.Column;
    import lombok.EqualsAndHashCode;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.io.Serializable;
    import java.time.LocalDate;

    @Getter
    @Setter
    @NoArgsConstructor
    @EqualsAndHashCode
    public class ItemConsumoId implements Serializable {

        @Column(name = "identificador", length = 50, nullable = false)
        private String identificador;

        @Column(name = "fecha_apertura", nullable = false)
        private LocalDate fechaApertura;

        @Column(name = "concepto", length = 300, nullable = false)
        private String concepto;
    }