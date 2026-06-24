package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;

@Entity
@Table(name = "miembro")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor
public class Miembro {

    @Id
    @Column(name = "cedula_miembro", length = 20, nullable = false, unique = true)
    private String cedulaMiembro;

    @Column(name = "nombres_completos", length = 200, nullable = false)
    private String nombresCompletos;

    @Column(name = "apellidos_completos", length = 200, nullable = false)
    private String apellidosCompletos;

    @Column(name = "sexo", length = 1, nullable = false)
    private String sexo;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "estado_cuenta", length = 30, nullable = false)
    private String estadoCuenta;

    @Column(name = "clave_hash", columnDefinition = "TEXT")
    private String claveHash;

    @Column(name = "fecha_cambio_clave")
    private Timestamp fechaCambioClave;

    @Column(name = "direccion_habitacion", columnDefinition = "TEXT", nullable = false)
    private String direccionHabitacion;

    @Column(name = "correo_institucional", length = 150, nullable = false, unique = true)
    private String correoInstitucional;

    @Column(name = "telefono_personal", length = 20, nullable = false)
    private String telefonoPersonal;

    @Column(name = "ultima_conexion")
    private Timestamp ultimaConexion;

    @Column(name = "indice_recurrencia", precision = 5, scale = 2)
    @ColumnDefault("0")
    private BigDecimal indiceRecurrencia;

    @Column(name = "fecha_apertura", nullable = false)
    @ColumnDefault("CURRENT_DATE")
    private Date fechaApertura;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "tipo_categoria", referencedColumnName = "tipo_categoria")
    private CategoriaFidelidad tipoCategoria;

    @Column(name = "intentos_fallidos")
    @ColumnDefault("0")
    private Integer intentosFallidos;

    @Column(name = "mfa_habilitado")
    @ColumnDefault("FALSE")
    private Boolean mfaHabilitado;

}